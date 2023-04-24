package com.example.socialmediaghale.domain.repository.content

import com.example.socialmediaghale.data.remote.dto.CategoryContentItem

interface GetContentByIdRepo {

    suspend fun getContentById(id: String): CategoryContentItem
}