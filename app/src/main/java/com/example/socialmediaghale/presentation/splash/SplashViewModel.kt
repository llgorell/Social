package com.example.socialmediaghale.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaghale.data.local.AuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val dataStore: AuthToken) : ViewModel() {

    init {
        checkToken()
    }
    fun checkToken(): Boolean {
        var check: Boolean = true
        viewModelScope.launch {
            dataStore.getToken()?.let {
                check = it.isEmpty()
            }
        }
        return check
    }
}