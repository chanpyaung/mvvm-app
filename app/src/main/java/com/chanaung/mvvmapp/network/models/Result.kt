package com.chanaung.mvvmapp.network.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("fields")
    val fields: List<Field>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("records")
    val records: List<Record>,
    @SerializedName("resource_id")
    val resourceId: String,
    @SerializedName("total")
    val total: Int
)