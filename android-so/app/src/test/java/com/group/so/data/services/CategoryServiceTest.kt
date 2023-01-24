package com.group.so.data.services

import com.group.so.data.MockResponseFileReader
import com.group.so.data.entities.request.CategoryDataRequest
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
class CategoryServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var service: CategoryService

    @Before
    fun createService() {
        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .build()
            .create(CategoryService::class.java)
    }
    @Test
    fun `should return correct endpoint get categories when receiving query`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("[]"))
            service.listCategoriesByName("teste")
            val request = mockWebServer.takeRequest()
            println(request.path)
            assertEquals(request.path, "/categories?name=teste")
        }
    }
    @Test
    fun `should return correct endpoint get categories`() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody(""))
            service.getAllCategories()
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/categories")
        }
    }
    @Test
    fun `should return correct endpoint register new category`() {
        runBlocking {
            val response = MockResponse()
            mockWebServer.enqueue(
                response.setBody(
                    "{\n" +
                        "    \"id\": 22,\n" +
                        "    \"name\": \"new name 1\"\n" +
                        "}"
                )
            )
            service.registerCategory(CategoryDataRequest(name = "teste1"))
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/categories")
        }
    }
    @Test
    fun `should return category list on success`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("category/category_sucess.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = service.getAllCategories()
        // Assert
        assertEquals(19, actualResponse.size)
    }

    @Test
    fun `should return an empty list when it has no category`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("category/category_sucess_empty.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = service.getAllCategories()

        // Assert
        assertEquals(0, actualResponse.size)
    }

    @Test(expected = HttpException::class)
    fun `It should return an error when it's 401`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
            .setBody(MockResponseFileReader("category/category_error_401.json").content)
        mockWebServer.enqueue(response)
        // Act
        service.getAllCategories()

        val request = mockWebServer.takeRequest()
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }
}
