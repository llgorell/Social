package com.example.socialmediaghale.presentation.content_discovery

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.socialmediaghale.data.remote.dto.CategoryContentItem

data class ContentState(
    val list: MutableState<List<CategoryContentItem>> = mutableStateOf(emptyList()),
    val filter: String = "",

    )
