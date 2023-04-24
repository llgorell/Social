package com.example.socialmediaghale.domain.repository.category

import com.example.socialmediaghale.data.remote.dto.Categories

interface CategoriesRepository {

    suspend fun getCategories(): Categories
}