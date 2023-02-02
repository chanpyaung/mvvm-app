package com.chanaung.mvvmapp.repository

import android.util.Log
import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.data.QuarterlyUsage
import com.chanaung.mvvmapp.network.api.GovDataSetApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface DataUsageRepository {
    suspend fun getDataUsage(): DataUsage

    fun getQuarterlyUsageOfAYear(year: Int): List<QuarterlyUsage>

}

class DataUsageRepositoryImpl(private val api: GovDataSetApiService): DataUsageRepository {

    override suspend fun getDataUsage(): DataUsage {
        withContext(Dispatchers.IO) {
            val dataUsageResponse = api.dataStoreSearch("a807b7ab-6cad-4aa6-87d0-e283a7353a0f", 0, 10)
            Log.d("DATA_USAGE: ", dataUsageResponse.result.toString())
        }
        return DataUsage(2018, 0.0, emptyList())
    }

    override fun getQuarterlyUsageOfAYear(year: Int): List<QuarterlyUsage> {
        TODO("Not yet implemented")
    }

}