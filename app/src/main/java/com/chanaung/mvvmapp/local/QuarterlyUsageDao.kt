package com.chanaung.mvvmapp.local

import androidx.room.*

@Dao
interface QuarterlyUsageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quarterlyUsagesEntities: List<QuarterlyUsagesEntity>)

    @Delete
    suspend fun delete(quarterlyUsagesEntity: QuarterlyUsagesEntity)

    @Query("SELECT * FROM quarterly_usages")
    suspend fun getAll(): List<QuarterlyUsagesEntity>

}