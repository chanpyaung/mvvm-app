package com.chanaung.mvvmapp.data

data class DataUsage(
    val year: Int,
    val totalUsage: Double,
    val quarterlyUsages: List<QuarterlyUsage>
)