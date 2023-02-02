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
) {
    override fun toString(): String {
        return "Result(fields=$fields, limit=$limit, links=$links, offset=$offset, records=$records, resourceId='$resourceId', total=$total)"
    }
}