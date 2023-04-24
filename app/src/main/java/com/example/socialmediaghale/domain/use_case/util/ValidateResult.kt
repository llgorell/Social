package com.example.socialmediaghale.domain.use_case.util

data class ValidateResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)
