package com.example.socialmediaghale.domain.repository.content

import com.example.socialmediaghale.data.remote.dto.CategoryContentItem

interface CategoryContentRepo {

    suspend fun getCategoryContent(): List<CategoryContentItem>
}