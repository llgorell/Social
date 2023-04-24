package com.example.socialmediaghale.di

import com.example.socialmediaghale.data.remote.SocialApi
import com.example.socialmediaghale.data.repository.category.CategoriesRepoImpl
import com.example.socialmediaghale.data.repository.category.FilterCategoriesRepoImpl
import com.example.socialmediaghale.data.repository.content.CategoryContentRepoImpl
import com.example.socialmediaghale.data.repository.content.GetContentByIdRepoImpl
import com.example.socialmediaghale.data.repository.content.LikeContentRepoImpl
import com.example.socialmediaghale.data.repository.login.SignInRepoImpl
import com.example.socialmediaghale.domain.repository.category.CategoriesRepository
import com.example.socialmediaghale.domain.repository.category.FilterCategories
import com.example.socialmediaghale.domain.repository.content.CategoryContentRepo
import com.example.socialmediaghale.domain.repository.content.GetContentByIdRepo
import com.example.socialmediaghale.domain.repository.content.LikeContentRepo
import com.example.socialmediaghale.domain.repository.login.SignInRepository
import com.example.socialmediaghale.domain.use_case.util.ValidateEmail
import com.example.socialmediaghale.domain.use_case.util.ValidatePassword
import com.example.socialmediaghale.domain.use_case.util.Validation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Provides
    @Singleton
    fun provideCategoryContent(api: SocialApi): CategoryContentRepo {
        return CategoryContentRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideCategories(api: SocialApi): CategoriesRepository {
        return CategoriesRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideFilterCategory(api: SocialApi): FilterCategories {
        return FilterCategoriesRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideSingIn(api: SocialApi): SignInRepository {
        return SignInRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetContentById(api: SocialApi): GetContentByIdRepo {
        return GetContentByIdRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideLikeContent(api: SocialApi): LikeContentRepo {
        return LikeContentRepoImpl(api)
    }


}