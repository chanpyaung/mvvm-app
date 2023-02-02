package com.chanaung.mvvmapp.di

import com.chanaung.mvvmapp.network.api.GovDataSetApiService
import com.chanaung.mvvmapp.repository.DataUsageRepository
import com.chanaung.mvvmapp.repository.DataUsageRepositoryImpl
import com.chanaung.mvvmapp.viewmodels.DataUsageViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<DataUsageRepository> { DataUsageRepositoryImpl(get()) }
    viewModel { DataUsageViewModel(get()) }
}


val networkModule = module {

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://data.gov.sg/api/action/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideGovDataSetApiService(retrofit: Retrofit): GovDataSetApiService = retrofit.create(GovDataSetApiService::class.java)

    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideGson() }
    single { provideGovDataSetApiService(get()) }
}