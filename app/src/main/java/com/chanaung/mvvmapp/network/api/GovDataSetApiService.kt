package com.chanaung.mvvmapp.network.api

import com.chanaung.mvvmapp.network.models.DataUsageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GovDataSetApiService {

    @GET("/datastore_search")
    suspend fun dataStoreSearch(
        @Query("resource_id") resourceId: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): DataUsageResponse

}