import com.group.so.data.entities.request.AuthContent
import com.group.so.data.entities.request.AuthDataRequest
import com.group.so.data.services.UserService
import com.group.so.mock.UserMock.convertToJson
import com.group.so.mock.UserMock.userDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotSame

@RunWith(JUnit4::class)
class UserServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var service: UserService

    @Before
    fun createService() {
        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .build()
            .create(UserService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should return correct endpoint`() {
        runBlocking {
            val response = mockResponse()
            mockWebServer.enqueue(response)
            service.login(AuthDataRequest(user = AuthContent("", "")))
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/users/sign_in")
        }
    }

    @Test
    fun `should return an error when the endpoint is wrong`() {

        runBlocking {
            val response = mockResponse()

            mockWebServer.enqueue(
                response
            )
            service.login(AuthDataRequest(user = AuthContent("", "")))
            val request: RecordedRequest = mockWebServer.takeRequest()
            print(request.path)
            assertNotSame(request.path, "sign_in")
        }
    }

    @Test
    fun `should return the token in the header after login`() {

        val response = mockResponse()

        runBlocking {

            mockWebServer.enqueue(
                response
            )
            mockWebServer.enqueue(
                response
            )
            service.login(AuthDataRequest(user = AuthContent("", "")))
            val request: RecordedRequest = mockWebServer.takeRequest()

            println(request.headers)
            assertEquals(
                response.headers.get("Authorization"), "TOKENXXXXX"
            )
        }
    }

    private fun mockResponse(): MockResponse {
        val response = MockResponse()
            .addHeader("Authorization", "TOKENXXXXX")
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(convertToJson(userDto))
        return response
    }
}
