package com.example.socialmediaghale.data.repository.category

import com.example.socialmediaghale.data.remote.SocialApi
import com.example.socialmediaghale.data.remote.dto.Categories
import com.example.socialmediaghale.data.remote.dto.CategoryContentItem
import com.example.socialmediaghale.data.remote.dto.FilterModel
import com.example.socialmediaghale.domain.repository.category.FilterCategories
import javax.inject.Inject

class FilterCategoriesRepoImpl @Inject constructor(private val api: SocialApi) : FilterCategories {

    override suspend fun filterCategories(categories: FilterModel): List<CategoryContentItem> {
        return api.filterCategories(categories = categories)
    }
}