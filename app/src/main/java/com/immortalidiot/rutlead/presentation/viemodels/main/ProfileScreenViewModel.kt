package com.immortalidiot.rutlead.presentation.viemodels.main

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.immortalidiot.rutlead.ui.models.ChangeGroupModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileScreenViewModel : ViewModel() {
    @Immutable
    sealed class State {
        object Init : State()
        object LogoutDialog : State()
        object ChangeGroupDialog : State()
    }

    var mutableState = MutableStateFlow<State>(State.Init)
        private set

    private val _uiState = MutableStateFlow(ChangeGroupModel(group = String()))
    val uiState: StateFlow<ChangeGroupModel> = _uiState.asStateFlow()

    fun changeLogoutDialogVisibility() {
        mutableState.value = State.LogoutDialog
    }

    fun onCancelled() {
        mutableState.value = State.Init
    }

    fun changeGroupDialogVisibility() {
        mutableState.value = State.ChangeGroupDialog
    }

    fun changeGroup(group: String) {
        _uiState.update {
            uiState.value.copy(group = group)
        }
    }
}
