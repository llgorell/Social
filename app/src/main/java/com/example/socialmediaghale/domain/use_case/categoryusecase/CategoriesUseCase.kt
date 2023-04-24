package com.example.socialmediaghale.domain.use_case.categoryusecase

import com.example.socialmediaghale.data.common.Resource
import com.example.socialmediaghale.data.remote.dto.Categories
import com.example.socialmediaghale.domain.repository.category.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(private val repository: CategoriesRepository) {

    operator fun invoke(): Flow<Resource<Categories>> = flow {

        try {
            emit(Resource.Loading())
            val categories = repository.getCategories()
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