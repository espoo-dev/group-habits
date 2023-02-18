package com.group.so.data.services

import com.group.so.data.ItemType
import com.group.so.data.MockResponseFileReader
import com.group.so.data.entities.request.service.ServiceDataRequest
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
class ItemServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var service: ItemService

    @Before
    fun createService() {
        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .build()
            .create(ItemService::class.java)
    }

    @Test
    fun `should return correct endpoint get item when receiving query`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.getItemByName("teste")
            val request = mockWebServer.takeRequest()
            println(request.path)
            assertEquals(request.path, "/items?name=teste")
        }
    }

    @Test
    fun `should return correct endpoint get item when receiving query name and item_type`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.getItemsByNameAndType("service", ItemType.SERVICE.value)
            val request = mockWebServer.takeRequest()
            println(request.path)
            assertEquals(request.path, "/items?name=service&item_type=service")
        }
    }

    @Test
    fun `should return correct endpoint get item when receiving query item_type`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.getItemsByItemType("teste")
            val request = mockWebServer.takeRequest()
            println(request.path)
            assertEquals(request.path, "/items?item_type=teste")
        }
    }

    @Test
    fun `should return correct endpoint items`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.getAllItems()
            val request = mockWebServer.takeRequest()
            print(request.path)
            assertEquals(request.path, "/items")
        }
    }

    @Test
    fun `should return item list on success`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("item/item_success.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = service.getAllItems()
        // Assert
        assertEquals(3, actualResponse.size)
    }

    @Test
    fun `should return an empty list when it has no item`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("item/item_sucess_empty.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = service.getAllItems()

        // Assert
        assertEquals(0, actualResponse.size)
    }

    @Test(expected = HttpException::class)
    fun ` should return an error if not authorized `() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
            .setBody(MockResponseFileReader("item/item_error_401.json").content)
        mockWebServer.enqueue(response)
        // Act
        service.getAllItems()

        val request = mockWebServer.takeRequest()
    }

    @Test
    fun `should return correct endpoint register new service`() {
        runBlocking {
            val response = MockResponse()
            mockWebServer.enqueue(
                response.setBody(
                    "{\n" +
                        "    \"id\": 13,\n" +
                        "    \"name\": \"service teste roanderson\",\n" +
                        "    \"extra_info\": \"service\",\n" +
                        "    \"sale_price\": 2000.5,\n" +
                        "    \"purchase_price\": 0.0,\n" +
                        "    \"item_type\": \"service\",\n" +
                        "    \"category\": null,\n" +
                        "    \"sales_unit\": null\n" +
                        "}"
                )
            )
            service.registerService(
                ServiceDataRequest(
                    name = "service test roanderson",
                    extraInfo = "extra_info",
                    salePrice = 2000.50,
                    itemType = "service"

                )
            )
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/items")
        }
    }
    @Test
    fun `should return correct endpoint edit new item`() {
        runBlocking {
            val response = MockResponse()
            mockWebServer.enqueue(
                response.setBody(
                    "{\n" +
                            "    \"id\": 13,\n" +
                            "    \"name\": \"service teste roanderson\",\n" +
                            "    \"extra_info\": \"service\",\n" +
                            "    \"sale_price\": 2000.5,\n" +
                            "    \"purchase_price\": 0.0,\n" +
                            "    \"item_type\": \"service\",\n" +
                            "    \"category\": null,\n" +
                            "    \"sales_unit\": null\n" +
                            "}"
                )
            )
            service.editItem(
                1,
                ServiceDataRequest(
                    name = "service test roanderson",
                    extraInfo = "extra_info",
                    salePrice = 2000.50,
                    itemType = "service"

                )
            )
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/items/1")
        }
    }


    @Test
    fun `should return correct endpoint delete item`() {
        runBlocking {
            val response = MockResponse()
            mockWebServer.enqueue(
                response.setBody(
                    "[]"
                )
            )
            service.deleteItem(
                1
            )
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/items/1")
        }
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }
}
