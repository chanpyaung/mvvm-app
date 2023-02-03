package com.chanaung.mvvmapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.chanaung.mvvmapp.local.AppDatabase
import com.chanaung.mvvmapp.local.DataUsageDao
import com.chanaung.mvvmapp.local.QuarterlyUsageDao
import com.chanaung.mvvmapp.network.NoConnectionInterceptor
import com.chanaung.mvvmapp.network.api.GovDataSetApiService
import com.chanaung.mvvmapp.repository.DataUsageRepository
import com.chanaung.mvvmapp.repository.DataUsageRepositoryImpl
import com.chanaung.mvvmapp.repository.DataUsageUseCase
import com.chanaung.mvvmapp.repository.DataUsageUseCaseImpl
import com.chanaung.mvvmapp.viewmodels.DataUsageViewModel
import com.chanaung.mvvmapp.viewmodels.DetailsDataUsageViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<DataUsageUseCase> { DataUsageUseCaseImpl(get(), get(), get()) }
    viewModel { DataUsageViewModel(get()) }
    viewModel { DetailsDataUsageViewModel(get()) }
}


val networkModule = module {

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://data.gov.sg/api/action/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideOkHttpClient(noConnectionInterceptor: NoConnectionInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(noConnectionInterceptor)
            .build()
    }

    fun provideNoConnectionInterceptor(context: Context): NoConnectionInterceptor {
        return NoConnectionInterceptor(context)
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideGovDataSetApiService(retrofit: Retrofit): GovDataSetApiService = retrofit.create(GovDataSetApiService::class.java)

    single { provideNoConnectionInterceptor(androidContext()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideGson() }
    single { provideGovDataSetApiService(get()) }
}

val repositoryModule = module {
    fun provideDataUsageRepository(apiService: GovDataSetApiService): DataUsageRepository {
        return DataUsageRepositoryImpl(apiService)
    }

    single { provideDataUsageRepository(get()) }
}

val databaseModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "mvvm_app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDataUsageDao(database: AppDatabase): DataUsageDao = database.dataUsageDao
    fun provideQuarterlyUsageDao(database: AppDatabase): QuarterlyUsageDao = database.quarterlyUsageDao

    single { provideDatabase(androidApplication()) }
    single { provideDataUsageDao(get()) }
    single { provideQuarterlyUsageDao(get()) }
}