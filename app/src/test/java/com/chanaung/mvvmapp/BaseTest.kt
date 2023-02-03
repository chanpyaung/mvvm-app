package com.chanaung.mvvmapp

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.io.File

abstract class BaseTest : KoinTest {

    private lateinit var mockServer: MockWebServer
    @Before
    open fun setUp() {
        startMockServer()
    }

    fun getResponseFromJson(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName))
    )

    fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    private fun startMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    fun getMockServerUrl() = mockServer.url("/").toString()

    private fun shutdownMockServer() {
        mockServer.shutdown()
    }

    @After
    open fun shutDown() {
        shutdownMockServer()
        stopKoin()
    }
}