package com.example.socialmediaghale.di

import android.app.Application
import com.example.socialmediaghale.data.common.Constants
import com.example.socialmediaghale.data.local.AuthToken
import com.example.socialmediaghale.data.local.AuthTokenImpl
import com.example.socialmediaghale.data.remote.SocialApi
import com.example.socialmediaghale.data.remote.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthToken(app: Application): AuthToken {
        return AuthTokenImpl(app)
    }


    @Singleton
    @Provides
    fun provideInterceptor(authToken: AuthToken): Interceptor {
        return AuthInterceptor(authToken)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): SocialApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}