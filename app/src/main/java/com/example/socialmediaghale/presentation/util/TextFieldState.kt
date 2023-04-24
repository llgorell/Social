package com.example.socialmediaghale.presentation.util

data class TextFieldState(
    val text: String = "",
    val hint: String = "",
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val isHintVisible: Boolean = true,
)
