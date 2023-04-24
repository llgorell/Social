package com.example.socialmediaghale.domain.use_case.util

import android.util.Patterns
import javax.inject.Inject

class ValidateEmail @Inject constructor() : Validation {
    override fun execute(input: String): ValidateResult {
        if (input.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = "the input cant Blank"
            )
        }
        /*     if (!Patterns.EMAIL_ADDRESS.matcher(input).matches()){
                 return ValidateResult(
                     successful = false,
                     errorMessage = "That`s not a valid email"
                 )
             }*/
        return ValidateResult(
            successful = true
        )
    }
}