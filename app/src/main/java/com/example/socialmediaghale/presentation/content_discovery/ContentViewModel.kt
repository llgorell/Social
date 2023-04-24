package com.example.socialmediaghale.presentation.content_discovery

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaghale.data.common.Resource
import com.example.socialmediaghale.data.remote.dto.CategoryContentItem
import com.example.socialmediaghale.data.remote.dto.FilterModel
import com.example.socialmediaghale.domain.use_case.categoryusecase.FilterCategoriesUseCase
import com.example.socialmediaghale.domain.use_case.content_category.CategoryContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val getContent: CategoryContentUseCase,
    private val filterCategory: FilterCategoriesUseCase,

    ) : ViewModel() {

    private val _state = mutableStateOf(ContentState())
    val state: State<ContentState> = _state

    init {
        getContent()
    }

    private fun getContent() {
        viewModelScope.launch {
            getContent.invoke().collect() { it ->
                when (it) {
                    is Resource.Success -> {
                        it.data?.let {
                            _state.value = state.value.copy(
                                list = mutableStateOf(it)
                            )
                        }
                    }
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {

                    }
                }
            }
        }

    }

    fun onEvent(event: ContentEvent) {
        when (event) {
            is ContentEvent.OnFilterClick -> {
                viewModelScope.launch(Dispatchers.Default) {
                    if (event.category == "For You") {

                    }
                    filterCategory.invoke(FilterModel(listOf(event.category))).collect {
                        when (it) {
                            is Resource.Success -> {
                                Log.d("kaveh", "success")
                                it.data?.let {
                                    _state.value = state.value.copy(
                                        list = mutableStateOf(it)
                                    )
                                }
                            }
                            is Resource.Error -> {
                                Log.d("kaveh", it.message!!)
                            }
                            is Resource.Loading -> {
                                Log.d("kaveh", "it.message!!")
                            }
                        }
                    }
                }
            }
        }
    }
}