package com.example.socialmediaghale.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaghale.data.local.AuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authToken: AuthToken,
) : ViewModel() {

    fun onEvent(event: ProfileEvent) {

        when (event) {
            ProfileEvent.SighOut -> {
                viewModelScope.launch {
                    authToken.deleteToken()
                }
            }
        }
    }
}