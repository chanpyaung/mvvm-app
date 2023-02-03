package com.chanaung.mvvmapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chanaung.mvvmapp.network.api.GovDataSetApiService
import com.chanaung.mvvmapp.repository.DataUsageRepository
import com.chanaung.mvvmapp.repository.DataUsageRepositoryImpl
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import java.net.HttpURLConnection
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class UsageDataRepoTest : BaseTest() {

    private lateinit var mRepo: DataUsageRepository
    private val mNextValue = "/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=100&offset=100"
    private val mLimit = 5
    private val mTotal = 59

    val mockWebServer: MockWebServer by inject()
    val apiService: GovDataSetApiService by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun start() {
        super.setUp()
        startKoin {
            modules(testAppComponent(baseUrl = getMockServerUrl()))
        }
    }

    @Test
    fun testDataUsageRepoFetchDataCheck() = runBlocking {
        getResponseFromJson("sample_response.json", HttpURLConnection.HTTP_OK)
        mRepo = DataUsageRepositoryImpl(apiService)

        val response = mRepo.fetchDataUsage()
        assertNotNull(response)
        assertEquals(response.result.links.next, mNextValue)
        assertEquals(response.result.total, mTotal)
        assertNotEquals(response.result.limit, mLimit)
    }

}