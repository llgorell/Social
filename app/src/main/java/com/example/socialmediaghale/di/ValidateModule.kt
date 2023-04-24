package com.example.socialmediaghale.di

import com.example.socialmediaghale.domain.use_case.util.ValidateEmail
import com.example.socialmediaghale.domain.use_case.util.ValidatePassword
import com.example.socialmediaghale.domain.use_case.util.Validation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ValidateModule {

    @Binds
    @Singleton
    abstract fun bindValidateEmail(validateInput: ValidateEmail): Validation

    @Binds
    @Singleton
    abstract fun bindValidatePassword(validateInput: ValidatePassword): Validation
}