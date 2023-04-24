package com.example.socialmediaghale.domain.use_case.categoryusecase

import com.example.socialmediaghale.data.common.Resource
import com.example.socialmediaghale.data.remote.dto.Categories
import com.example.socialmediaghale.data.remote.dto.CategoryContentItem
import com.example.socialmediaghale.data.remote.dto.FilterModel
import com.example.socialmediaghale.domain.repository.category.FilterCategories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FilterCategoriesUseCase @Inject constructor(private val repository: FilterCategories) {

    operator fun invoke(categories: FilterModel): Flow<Resource<List<CategoryContentItem>>> = flow {

        try {
            val contentFilter = repository.filterCategories(categories = categories)
            emit(Resource.Success(contentFilter))
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