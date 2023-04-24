package com.example.socialmediaghale.data.remote


import com.example.socialmediaghale.data.remote.dto.*
import com.example.socialmediaghale.domain.model.LoginData
import retrofit2.http.*

interface SocialApi {

    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun signIn(@Body login: LoginData): Token

    @GET("content")
    suspend fun getCategoryContent(): List<CategoryContentItem>

    @GET("categories")
    suspend fun getCategories(): Categories

    @POST("content/filter")
    suspend fun filterCategories(@Body categories: FilterModel): List<CategoryContentItem>

    @POST("content/{id}/like")
    suspend fun likeContent(@Path(value = "id", encoded = true) id: String): Like

    @GET("content/{id}")
    suspend fun getContentById(@Path(value = "id", encoded = true) id: String): CategoryContentItem


}
