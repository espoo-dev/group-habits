package com.group.so.data.di.interceptor

import com.group.so.data.services.SessionManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection

class AuthInterceptor constructor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var accessToken = sessionManager.readAPIToken()

        val response = chain.proceed(newRequestWithAccessToken(accessToken, request))

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            val newAccessToken = sessionManager.readAPIToken()
            if (newAccessToken != accessToken) {
                return chain.proceed(newRequestWithAccessToken(accessToken, request))
            } else {
                // accessToken = refreshToken()
                if (accessToken.isNullOrBlank()) {
                    sessionManager.logout()
                    return response
                }
                return chain.proceed(newRequestWithAccessToken(accessToken, request))
            }
        }

        return response
    }

    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
        request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
}
