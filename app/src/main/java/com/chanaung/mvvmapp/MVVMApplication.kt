package com.chanaung.mvvmapp

import android.app.Application
import com.chanaung.mvvmapp.di.appModule
import com.chanaung.mvvmapp.di.databaseModule
import com.chanaung.mvvmapp.di.networkModule
import com.chanaung.mvvmapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

open class MVVMApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MVVMApplication)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    databaseModule,
                    repositoryModule
                )
            )

        }
    }

}