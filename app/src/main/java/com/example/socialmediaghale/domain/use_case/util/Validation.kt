package com.example.socialmediaghale.domain.use_case.util

interface Validation {
    fun execute(input: String): ValidateResult
}