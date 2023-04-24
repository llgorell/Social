package com.example.socialmediaghale.presentation.sign_in

import androidx.compose.ui.focus.FocusState

sealed class OnSignInEvent {
    data class EnteredEmail(val value: String) : OnSignInEvent()
    data class ChangeFocusEmail(val focusState: FocusState) : OnSignInEvent()

    data class EnteredPassword(val value: String) : OnSignInEvent()
    data class ChangeFocusPassword(val focusState: FocusState) : OnSignInEvent()

    object Login : OnSignInEvent()
}
