package com.chanaung.mvvmapp.network.models


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("next")
    val next: String,
    @SerializedName("start")
    val start: String
)