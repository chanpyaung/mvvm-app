package com.chanaung.mvvmapp.repository

import com.chanaung.mvvmapp.data.DataUsage
import com.chanaung.mvvmapp.data.QuarterlyUsage

interface DataUsageRepository {

    fun getDataUsage(): DataUsage

    fun getQuarterlyUsageOfAYear(year: Int): List<QuarterlyUsage>

}

class DataUsageRepositoryImpl: DataUsageRepository {

    override fun getDataUsage(): DataUsage {
        TODO("Not yet implemented")
    }

    override fun getQuarterlyUsageOfAYear(year: Int): List<QuarterlyUsage> {
        TODO("Not yet implemented")
    }

}