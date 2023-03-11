package com.group.so.data.services

import com.group.so.data.MockResponseFileReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class SalesUnitServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var service: SalesUnitsService

    @Before
    fun createService() {
        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .build()
            .create(SalesUnitsService::class.java)
    }

    @Test
    fun `should return correct endpoint get sale units`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.getAllSalesUnit()
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/sales_units")
        }
    }

    @Test
    fun `should return sale unit list on success`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("salesUnit/sales_unit_success.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = service.getAllSalesUnit()
        // Assert
        assertEquals(2, actualResponse.size)
    }

    @Test
    fun `should return an empty list when it has no sales unit`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("salesUnit/sales_unit_empty.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = service.getAllSalesUnit()

        // Assert
        assertEquals(0, actualResponse.size)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }
}
