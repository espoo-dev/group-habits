package com.group.so.data.services

import com.group.so.data.MockResponseFileReader
import com.group.so.data.entities.request.customer.CustomerDataRequest
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
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CustomerServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var service: CustomerService

    @Before
    fun createService() {
        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .build()
            .create(CustomerService::class.java)
    }

    @Test
    fun `should return correct endpoint get customer when receiving query`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.getCustomersByName("teste")
            val request = mockWebServer.takeRequest()
            println(request.path)
            assertEquals(request.path, "/customers?name=teste")
        }
    }

    @Test
    fun `should return correct endpoint get customer when receiving query customer_type`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.getCustomersByCustomerType("teste")
            val request = mockWebServer.takeRequest()
            println(request.path)
            assertEquals(request.path, "/customers?customer_type=teste")
        }
    }

    @Test
    fun `should return correct endpoint customers`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.getAllCustomers()
            val request = mockWebServer.takeRequest()
            print(request.path)
            assertEquals(request.path, "/customers")
        }
    }

    @Test
    fun `should return customer list on success`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("customer/customer_success.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = service.getAllCustomers()
        // Assert
        assertEquals(3, actualResponse.size)
    }

    @Test
    fun `should return an empty list when it has no customer`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("customer/customer_sucess_empty.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = service.getAllCustomers()

        // Assert
        assertEquals(0, actualResponse.size)
    }

    @Test(expected = HttpException::class)
    fun ` should return an error if not authorized `() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
            .setBody(MockResponseFileReader("customer/customer_error_401.json").content)
        mockWebServer.enqueue(response)
        // Act
        service.getAllCustomers()

        val request = mockWebServer.takeRequest()
    }

    @Test
    fun `should return correct endpoint register new customer`() {
        runBlocking {
            val response = MockResponse()
            mockWebServer.enqueue(
                response.setBody(
                    "{\n" +
                        "    \"id\": 4,\n" +
                        "    \"name\": \"new_customer_name2221\",\n" +
                        "    \"document_number\": \"123456789\",\n" +
                        "    \"phone\": \"85936189085\",\n" +
                        "    \"state_inscription\": \"something\",\n" +
                        "    \"customer_type\": \"business\"\n" +
                        "}"
                )
            )
            service.registerCustomer(
                CustomerDataRequest(
                    name = "new_customer_name2221",
                    documentNumber = "123456789",
                    phone = "85936189085",
                    stateInscription = "something",
                    customeType = "business"
                )
            )
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/customers")
        }
    }

    @Test
    fun `should return correct endpoint delete customer`() {
        runBlocking {
            val response = MockResponse()
            mockWebServer.enqueue(
                response.setBody(
                    "[]"
                )
            )
            service.deleteCustomer(
                1
            )
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/customers/1")
        }
    }

    @Test
    fun `should return correct endpoint edit new customer`() {
        runBlocking {
            val response = MockResponse()
            mockWebServer.enqueue(
                response.setBody(
                    "{\n" +
                        "    \"id\": 4,\n" +
                        "    \"name\": \"new_customer_name2221\",\n" +
                        "    \"document_number\": \"123456789\",\n" +
                        "    \"phone\": \"85936189085\",\n" +
                        "    \"state_inscription\": \"something\",\n" +
                        "    \"customer_type\": \"business\"\n" +
                        "}"
                )
            )
            service.editCustomer(
                1,
                CustomerDataRequest(
                    name = "new_customer_name2221",
                    documentNumber = "123456789",
                    phone = "85936189085",
                    stateInscription = "something",
                    customeType = "business"
                )
            )
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/customers/1")
        }
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }
}
