package com.chanaung.mvvmapp

import com.chanaung.mvvmapp.di.appModule
import com.chanaung.mvvmapp.di.databaseModule
import com.chanaung.mvvmapp.network.api.GovDataSetApiService
import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun testAppComponent(baseUrl: String) = listOf(
    provideRetrofitTest(baseUrl),
    mockWebServerTest,
    appModule,
    databaseModule
)

val mockWebServerTest = module {
    factory {
        MockWebServer()
    }

}

fun provideRetrofitTest(baseUrl: String) = module {
    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
    factory {
        get<Retrofit>().create(GovDataSetApiService::class.java)
    }
}


