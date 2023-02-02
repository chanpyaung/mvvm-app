package com.chanaung.mvvmapp.local

import androidx.room.*

@Dao
interface DataUsageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dataUsageEntities: List<DataUsageEntity>)

    @Delete
    suspend fun delete(dataUsageEntity: DataUsageEntity)

    @Query("SELECT * FROM data_usages")
    suspend fun getAll(): List<DataUsageEntity>

    @Transaction
    @Query("SELECT * FROM data_usages")
    suspend fun getYearsWithQuarterlyUsages(): List<DataUsageWithQuarterlyRecords>

    @Transaction
    @Query("SELECT * FROM data_usages WHERE year = :year LIMIT 1")
    suspend fun getAYearWithQuarterlyUsages(year: Int): DataUsageWithQuarterlyRecords?
}