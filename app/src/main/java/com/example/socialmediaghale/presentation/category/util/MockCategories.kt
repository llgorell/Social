package com.example.socialmediaghale.presentation.category.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class MockCategories(
    val category: String,
    var selected: MutableState<Boolean> = mutableStateOf(false),
)
