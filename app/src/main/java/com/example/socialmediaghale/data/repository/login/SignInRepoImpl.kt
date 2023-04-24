package com.example.socialmediaghale.data.repository.login

import com.example.socialmediaghale.data.remote.SocialApi
import com.example.socialmediaghale.data.remote.dto.Token
import com.example.socialmediaghale.domain.model.LoginData
import com.example.socialmediaghale.domain.repository.login.SignInRepository
import javax.inject.Inject

class SignInRepoImpl @Inject constructor(val api: SocialApi) : SignInRepository {
    override suspend fun signIn(loginData: LoginData): Token {
        return api.signIn(loginData)
    }

}