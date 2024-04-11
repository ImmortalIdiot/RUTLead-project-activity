package com.immortalidiot.rutlead.presentation.viemodels.main

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileScreenViewModel : ViewModel() {
    @Immutable
    sealed class State {
        object Init : State()
        object LogoutDialog : State()
        object ChangeGroupDialog : State()
    }

    var mutableState = MutableStateFlow<State>(State.Init)
        private set

    fun changeLogoutDialogVisibility() {
        mutableState.value = State.LogoutDialog
    }

    fun onCancelled() {
        mutableState.value = State.Init
    }

    fun changeGroupDialogVisibility() {
        mutableState.value = State.ChangeGroupDialog
    }
}
