package com.immortalidiot.rutlead.presentation.viemodels.main

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.immortalidiot.rutlead.ui.models.ChangeGroupModel
import com.immortalidiot.rutlead.validation.validateGroup
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
        data class GroupValidationError(val groupError: String?) : State()
    }

    var mutableState = MutableStateFlow<State>(State.Init)
        private set

    private val _uiState = MutableStateFlow(
        ChangeGroupModel(
            group = String(),
            isGroupFieldFocused = false
        )
    )
    val uiState: StateFlow<ChangeGroupModel> = _uiState.asStateFlow()

    fun clearErrorStack() { mutableState.value = State.ChangeGroupDialog }

    fun changeLogoutDialogVisibility() { mutableState.value = State.LogoutDialog }

    fun onCancelled() { mutableState.value = State.Init }

    fun changeGroupDialogVisibility() { mutableState.value = State.ChangeGroupDialog }

    fun changeGroup(group: String) { _uiState.update { uiState.value.copy(group = group) } }

    fun changeGroup() {
        val group = _uiState.value.group.validateGroup()

        if (group.isFailure) {
            mutableState.update {
                State.GroupValidationError(groupError = group.exceptionOrNull()?.message)
            }
        } else {
            // TODO(): add post request for change a group
            mutableState.value = State.Init
        }
    }
}
