package com.example.socialmediaghale.presentation.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.socialmediaghale.data.remote.dto.Categories
import com.example.socialmediaghale.presentation.category.util.MockCategories

data class CategoryState(
    val categories: Categories? = null,
    val mockData: List<MockCategories> = emptyList(),
    val isLoading: Boolean = true,
    val filterList: List<String> = emptyList(),
    var selectFilterSize: MutableState<Boolean> = mutableStateOf(false),
)
