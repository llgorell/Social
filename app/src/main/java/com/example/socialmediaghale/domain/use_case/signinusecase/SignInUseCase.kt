package com.example.socialmediaghale.domain.use_case.signinusecase

import com.example.socialmediaghale.data.common.Resource
import com.example.socialmediaghale.data.remote.dto.Token
import com.example.socialmediaghale.domain.model.LoginData
import com.example.socialmediaghale.domain.repository.login.SignInRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: SignInRepository) {

    operator fun invoke(loginData: LoginData): Flow<Resource<Token>> = flow {

        try {
            emit(Resource.Loading())
            val token = repository.signIn(loginData)
            emit(Resource.Success(token))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected error"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.message ?: "Could`nt reach server. check your internet connection"
                )
            )
        }
    }
}