package com.example.socialmediaghale.data.repository.content

import com.example.socialmediaghale.data.remote.SocialApi
import com.example.socialmediaghale.data.remote.dto.CategoryContentItem
import com.example.socialmediaghale.domain.repository.content.CategoryContentRepo
import javax.inject.Inject

class CategoryContentRepoImpl @Inject constructor(private val api: SocialApi) :
    CategoryContentRepo {
    override suspend fun getCategoryContent(): List<CategoryContentItem> {
        return api.getCategoryContent()
    }
}