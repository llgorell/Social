package com.example.socialmediaghale.data.repository.content

import com.example.socialmediaghale.data.remote.SocialApi
import com.example.socialmediaghale.data.remote.dto.CategoryContentItem
import com.example.socialmediaghale.domain.repository.content.GetContentByIdRepo
import javax.inject.Inject

class GetContentByIdRepoImpl @Inject constructor(private val api: SocialApi) : GetContentByIdRepo {
    override suspend fun getContentById(id: String): CategoryContentItem {
        return api.getContentById(id)
    }
}