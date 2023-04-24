package com.example.socialmediaghale.domain.use_case.content_category

import com.example.socialmediaghale.data.common.Resource
import com.example.socialmediaghale.data.remote.dto.CategoryContentItem
import com.example.socialmediaghale.domain.repository.content.CategoryContentRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryContentUseCase @Inject constructor(private val repository: CategoryContentRepo) {

    operator fun invoke(): Flow<Resource<List<CategoryContentItem>>> = flow {

        try {
            emit(Resource.Loading())
            val categories = repository.getCategoryContent()
            emit(Resource.Success(categories))
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