package com.example.socialmediaghale.data.remote.interceptor

import com.example.socialmediaghale.data.local.AuthToken
import com.example.socialmediaghale.data.local.AuthTokenImpl
import kotlinx.coroutines.*
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val authToken: AuthToken) : Interceptor {

    private var token: String = ""
    fun getToken() {

        runBlocking {
            token = authToken.getToken().toString()
        }
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        getToken()
        val request = chain.request().newBuilder()
            .header("Authorization", token)
            .build()
        return chain.proceed(request)
    }
}