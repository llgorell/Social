package com.example.socialmediaghale.presentation.category

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaghale.data.common.Resource
import com.example.socialmediaghale.data.remote.dto.Categories
import com.example.socialmediaghale.data.remote.dto.FilterModel
import com.example.socialmediaghale.domain.use_case.categoryusecase.CategoriesUseCase
import com.example.socialmediaghale.domain.use_case.categoryusecase.FilterCategoriesUseCase
import com.example.socialmediaghale.presentation.category.util.MockCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategory: CategoriesUseCase,
    private val postCategory: FilterCategoriesUseCase,
) : ViewModel() {

    //get categories and show user
    //select categories and countinue to main screen
    init {
        getCategories()
    }

    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state

     val list = mutableListOf<String>()
    private val mockList = mutableListOf<MockCategories>()

    private val _eventSharedFlow = MutableSharedFlow<CategoryViewModel.UiEvent>()
    val eventSharedFlow = _eventSharedFlow.asSharedFlow()



    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategory.invoke().collect() {
                when (it) {
                    is Resource.Success -> {
                        Log.d("kaveh", "suces")
                        it.data?.forEach {
                            mockList.add(MockCategories(it))
                        }
                        _state.value = state.value.copy(
                            categories = it.data,
                            mockData = mockList,
                            isLoading = false
                        )

                    }
                    is Resource.Error -> {
                        Log.d("kaveh", it.message!!)
                        _state.value = state.value.copy(
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        /*      _state.value = state.value.copy(
                                  isLoading = true
                              )*/

                    }
                }
            }
        }

    }

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.OnClickCategory -> {
                if (list.size<5){
                    _state.value = state.value.copy(
                        selectFilterSize = mutableStateOf(false)
                    )
                }else{
                    _state.value = state.value.copy(
                        selectFilterSize = mutableStateOf(true)
                    )
                }
                if (list.contains(event.category)) {
                    list.remove(event.category)
                } else {
                    list.add(event.category)
                }
                _state.value = state.value.copy(
                    filterList = list
                )
            }
            CategoryEvent.Continue -> {
                viewModelScope.launch {
                    if (list.size < 5) {
                        _eventSharedFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = "at least 5"
                            )
                        )
                    } else {

                        val job = async { postCategory.invoke(FilterModel(list)) }
                        job.await()
                        _eventSharedFlow.emit(UiEvent.GoContinue)
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object GoContinue : UiEvent()
    }
}