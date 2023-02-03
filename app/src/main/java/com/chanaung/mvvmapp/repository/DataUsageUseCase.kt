package com.chanaung.mvvmapp.repository

import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.data.QuarterlyUsage
import com.chanaung.mvvmapp.local.*
import com.chanaung.mvvmapp.network.api.GovDataSetApiService
import com.chanaung.mvvmapp.network.models.DataUsageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface DataUsageUseCase {
    suspend fun getDataUsages(): List<DataUsage>

    suspend fun getQuarterlyUsageOfAYear(year: Int): List<QuarterlyUsage>

    suspend fun fetchDataUsages()

    fun getResponse(): DataUsageResponse
}

class DataUsageUseCaseImpl(private val repo: DataUsageRepository, private val dataUsageDao: DataUsageDao, private val quarterlyUsageDao: QuarterlyUsageDao): DataUsageUseCase {

    private lateinit var response: DataUsageResponse
    override fun getResponse() = response
    override suspend fun fetchDataUsages() {
        withContext(Dispatchers.IO) {
            try {
                val dataUsageResponse = repo.fetchDataUsage()
                response = dataUsageResponse
                val records = dataUsageResponse.result.records
                val yearMap = hashMapOf<Int, Double>()
                val quarterlyUsageEntities = arrayListOf<QuarterlyUsagesEntity>()
                records.forEachIndexed { index, record ->
                    val yearAndQuarter = record.quarter.split("-")
                    val year = yearAndQuarter[0].toInt()
                    val quarter = yearAndQuarter[1]
                    val mobileData = record.volumeOfMobileData.toDouble()
                    if (yearMap.containsKey(year)) {
                        yearMap[year] = (yearMap[year] ?: 0.0).plus(mobileData)
                    } else {
                        yearMap[year] = mobileData
                    }
                    quarterlyUsageEntities.add(
                        QuarterlyUsagesEntity(
                            id = record.id,
                            yearOfEachQuarter = year,
                            quarter = quarter,
                            usage = mobileData
                        )
                    )
                }
                val dataUsageEntities = yearMap.map {
                    DataUsageEntity(it.key, it.value)
                }
                dataUsageDao.insertAll(dataUsageEntities)
                if (quarterlyUsageEntities.isNotEmpty()) {
                    quarterlyUsageDao.insertAll(quarterlyUsageEntities)
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }
    override suspend fun getDataUsages(): List<DataUsage> {
        return dataUsageDao.getYearsWithQuarterlyUsages().map {
            mappedDataUsageWithQuarterlyRecordsToDataUsage(it)
        }
    }

    override suspend fun getQuarterlyUsageOfAYear(year: Int): List<QuarterlyUsage> {
        return dataUsageDao.getAYearWithQuarterlyUsages(year)?.quarterlyUsages?.map {
            mappedQuarterlyUsageEntityToQuarterlyUsage(it)
        } ?: emptyList()
    }

    private fun mappedDataUsageWithQuarterlyRecordsToDataUsage(dataUsageWithQuarterlyRecords: DataUsageWithQuarterlyRecords): DataUsage {
        return DataUsage(
                year = dataUsageWithQuarterlyRecords.dataUsageEntity.year,
                totalUsage = dataUsageWithQuarterlyRecords.dataUsageEntity.totalUsage,
                quarterlyUsages = dataUsageWithQuarterlyRecords.quarterlyUsages.map { qUsage -> mappedQuarterlyUsageEntityToQuarterlyUsage(qUsage) }
            )
    }

    private fun mappedQuarterlyUsageEntityToQuarterlyUsage(quarterlyUsagesEntity: QuarterlyUsagesEntity): QuarterlyUsage = QuarterlyUsage(
        id = quarterlyUsagesEntity.id,
        quarter = quarterlyUsagesEntity.quarter,
        usage = quarterlyUsagesEntity.usage,
        year = quarterlyUsagesEntity.yearOfEachQuarter
    )
}