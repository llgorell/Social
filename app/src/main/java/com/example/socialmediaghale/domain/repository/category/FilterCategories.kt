package com.example.socialmediaghale.domain.repository.category

import com.example.socialmediaghale.data.remote.dto.CategoryContentItem
import com.example.socialmediaghale.data.remote.dto.FilterModel

interface FilterCategories {

    suspend fun filterCategories(categories: FilterModel): List<CategoryContentItem>
}