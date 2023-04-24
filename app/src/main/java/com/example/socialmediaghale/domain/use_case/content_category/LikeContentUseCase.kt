package com.example.socialmediaghale.domain.use_case.content_category

import com.example.socialmediaghale.data.common.Resource
import com.example.socialmediaghale.data.remote.dto.Like
import com.example.socialmediaghale.domain.repository.content.LikeContentRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LikeContentUseCase @Inject constructor(private val repository: LikeContentRepo) {

    operator fun invoke(id: String): Flow<Resource<Like>> = flow {
        try {
            emit(Resource.Loading())
            val messageLike = repository.likeContent(id)
            emit(Resource.Success(messageLike))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "An Unexpected error"))

        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.message ?: "Could`nt reach server. check your internet connection"
                )
            )
        }
    }
}