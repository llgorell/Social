package com.example.socialmediaghale.presentation.sign_in

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaghale.data.common.Resource
import com.example.socialmediaghale.data.local.AuthToken
import com.example.socialmediaghale.data.remote.interceptor.AuthInterceptor
import com.example.socialmediaghale.domain.model.LoginData
import com.example.socialmediaghale.domain.use_case.signinusecase.SignInUseCase
import com.example.socialmediaghale.domain.use_case.util.ValidateEmail
import com.example.socialmediaghale.domain.use_case.util.ValidatePassword
import com.example.socialmediaghale.presentation.util.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val useCase: SignInUseCase,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val dataStore: AuthToken,

    ) : ViewModel() {

    private val _email = mutableStateOf(TextFieldState())
    val email: State<TextFieldState> = _email

    private val _password = mutableStateOf(TextFieldState())
    val password: State<TextFieldState> = _password

    private val _eventSharedFlow = MutableSharedFlow<UiEvent>()
    val eventSharedFlow = _eventSharedFlow.asSharedFlow()


    fun onEvent(event: OnSignInEvent) {
        when (event) {
            is OnSignInEvent.ChangeFocusEmail -> {
                _email.value = email.value.copy(
                    isHintVisible = !event.focusState.isFocused && email.value.text.isBlank()
                )
            }
            is OnSignInEvent.ChangeFocusPassword -> {
                _password.value = password.value.copy(
                    isHintVisible = !event.focusState.isFocused && password.value.text.isBlank()
                )
            }
            is OnSignInEvent.EnteredEmail -> {
                _email.value = email.value.copy(
                    text = event.value
                )
            }
            is OnSignInEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value
                )
            }
            OnSignInEvent.Login -> login()
        }
    }

    fun login() {
        val inputEmail = validateEmail.execute(email.value.text)
        val inputPassword = validatePassword.execute(password.value.text)

        val hasError = listOf(
            inputEmail,
            inputPassword
        ).any { !it.successful }

        if (hasError) {
            _email.value = email.value.copy(
                errorMessage = inputEmail.errorMessage,
                isError = !inputEmail.successful,
                isHintVisible = inputEmail.successful
            )
            _password.value = password.value.copy(
                errorMessage = inputPassword.errorMessage,
                isError = !inputPassword.successful,
                isHintVisible = inputPassword.successful
            )
            return
        }
        viewModelScope.launch {
            val loginData = LoginData(
                username = email.value.text,
                password = password.value.text
            )
            useCase.invoke(loginData).collect() {
                when (it) {
                    is Resource.Success -> {
                        dataStore.saveToken(it.data!!.token)
                        Log.d("kaveh", dataStore.getToken()!!)
                        _eventSharedFlow.emit(UiEvent.SaveToken)
                    }
                    is Resource.Loading -> {}
                    is Resource.Error -> {
                        Log.d("kaveh", it.message!!)
                        _eventSharedFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = it.message!!
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveToken : UiEvent()
    }

}