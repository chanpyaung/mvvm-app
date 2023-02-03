package com.chanaung.mvvmapp.repository

import com.chanaung.mvvmapp.network.api.GovDataSetApiService
import com.chanaung.mvvmapp.network.models.DataUsageResponse

interface DataUsageRepository {
    suspend fun fetchDataUsage(): DataUsageResponse

}

class DataUsageRepositoryImpl(private val api: GovDataSetApiService): DataUsageRepository {
    override suspend fun fetchDataUsage(): DataUsageResponse =
        api.dataStoreSearch("a807b7ab-6cad-4aa6-87d0-e283a7353a0f", 0, 100)
}