package com.example.socialmediaghale.data.repository.category

import com.example.socialmediaghale.data.remote.SocialApi
import com.example.socialmediaghale.data.remote.dto.Categories
import com.example.socialmediaghale.domain.repository.category.CategoriesRepository
import javax.inject.Inject

class CategoriesRepoImpl @Inject constructor(private val api: SocialApi) : CategoriesRepository {

    override suspend fun getCategories(): Categories {
        return api.getCategories()
    }
}