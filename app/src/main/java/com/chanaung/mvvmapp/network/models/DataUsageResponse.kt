package com.chanaung.mvvmapp.network.models


import com.google.gson.annotations.SerializedName

data class DataUsageResponse(
    @SerializedName("help")
    val help: String,
    @SerializedName("result")
    val result: Result,
    @SerializedName("success")
    val success: Boolean
)