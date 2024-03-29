package com.group.so.data.services

import com.group.so.data.MockResponseFileReader
import com.group.so.data.entities.request.serviceOrder.ServiceOrderDataRequest
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
class ServiceOrderServiceTest {

    private lateinit var mockWebServer: MockWebServer
    lateinit var service: ServiceOrderService

    @Before
    fun createService() {
        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .build()
            .create(ServiceOrderService::class.java)
    }

    @Test
    fun `should return correct endpoint get service orders`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.getAllServiceOrders()
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/service_orders")
        }
    }

    @Test
    fun `should return service orders list on success`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("serviceOrder/service_order_success.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = service.getAllServiceOrders()
        // Assert
        assertEquals(3, actualResponse.size)
    }

    @Test
    fun `should return an empty list when it has no services orders`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("serviceOrder/service_order_empty.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = service.getAllServiceOrders()

        // Assert
        assertEquals(0, actualResponse.size)
    }

    @Test
    fun `should return correct endpoint edit service order`() {
        runBlocking {
            val response = MockResponse()
            mockWebServer.enqueue(
                response.setBody(
                    "{\n" +
                        "    \"id\": 4,\n" +
                        "    \"extra_info\": \"some items 3\",\n" +
                        "    \"status\": \"budge\",\n" +
                        "    \"creation_date\": null,\n" +
                        "    \"conclusion_date\": null,\n" +
                        "    \"discount\": 13.0,\n" +
                        "    \"customer\": {\n" +
                        "      \"id\": 17,\n" +
                        "      \"name\": \"test\",\n" +
                        "      \"document_number\": \"04590651564\",\n" +
                        "      \"phone\": \"88915484544\",\n" +
                        "      \"state_inscription\": \"\",\n" +
                        "      \"customer_type\": \"person\"\n" +
                        "    }\n" +
                        "  }"
                )
            )
            service.editServiceOrder(
                4,
                ServiceOrderDataRequest(
                    extraInfo = "some items 4",
                    discount = 13.00,
                    status = "budge",
                    customer = 17,
                    creationDate = null,
                    conclusionDate = null,
                    items = listOf(0, 1)

                )
            )
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/service_orders/4")
        }
    }

    @Test
    fun `should return correct endpoint register new service order`() {
        runBlocking {
            val response = MockResponse()
            mockWebServer.enqueue(
                response.setBody(
                    "{\n" +
                        "    \"id\": 4,\n" +
                        "    \"extra_info\": \"some items 3\",\n" +
                        "    \"status\": \"budge\",\n" +
                        "    \"creation_date\": null,\n" +
                        "    \"conclusion_date\": null,\n" +
                        "    \"discount\": 13.0,\n" +
                        "    \"customer\": {\n" +
                        "      \"id\": 17,\n" +
                        "      \"name\": \"test\",\n" +
                        "      \"document_number\": \"04590651564\",\n" +
                        "      \"phone\": \"88915484544\",\n" +
                        "      \"state_inscription\": \"\",\n" +
                        "      \"customer_type\": \"person\"\n" +
                        "    }\n" +
                        "  }"
                )
            )
            service.register(
                ServiceOrderDataRequest(
                    extraInfo = "some items 4",
                    discount = 13.00,
                    status = "budge",
                    customer = 17,
                    creationDate = null,
                    conclusionDate = null,
                    items = listOf(0, 1)

                )
            )
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/service_orders")
        }
    }
    @Test
    fun `should return correct endpoint delete service order`() {
        runBlocking {
            val response = MockResponse()
            mockWebServer.enqueue(
                response.setBody(
                    "[]"
                )
            )
            service.deleteServiceOrder(
                1
            )
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/service_order/1")
        }
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }
}
