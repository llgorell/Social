package com.example.socialmediaghale.data.local

interface AuthToken {

    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun deleteToken()

}