package com.chanaung.mvvmapp.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataUsageEntity::class, QuarterlyUsagesEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract val dataUsageDao: DataUsageDao
    abstract val quarterlyUsageDao: QuarterlyUsageDao

}