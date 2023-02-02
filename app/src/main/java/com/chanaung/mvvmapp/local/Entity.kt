package com.chanaung.mvvmapp.local

import androidx.room.*

@Entity(tableName = "data_usages")
data class DataUsageEntity(
    @PrimaryKey val year: Int,
    @ColumnInfo("totalUsage") val totalUsage: Double
)

@Entity(tableName = "quarterly_usages")
data class QuarterlyUsagesEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "quarter") val quarter: String,
    @ColumnInfo(name = "usage") val usage: Double,
    val yearOfEachQuarter: Int
)

data class DataUsageWithQuarterlyRecords(
    @Embedded val dataUsageEntity: DataUsageEntity,
    @Relation(
        parentColumn = "year",
        entityColumn = "yearOfEachQuarter"
    )
    val quarterlyUsages: List<QuarterlyUsagesEntity>
)