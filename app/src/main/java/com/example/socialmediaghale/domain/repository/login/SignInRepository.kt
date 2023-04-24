package com.example.socialmediaghale.domain.repository.login

import com.example.socialmediaghale.data.remote.dto.Token
import com.example.socialmediaghale.domain.model.LoginData

interface SignInRepository {

    suspend fun signIn(loginData: LoginData): Token
}