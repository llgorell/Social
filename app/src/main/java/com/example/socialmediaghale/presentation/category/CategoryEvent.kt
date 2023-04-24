package com.example.socialmediaghale.presentation.category

sealed class CategoryEvent {
    data class OnClickCategory(val category: String) : CategoryEvent()
    object Continue : CategoryEvent()
}
