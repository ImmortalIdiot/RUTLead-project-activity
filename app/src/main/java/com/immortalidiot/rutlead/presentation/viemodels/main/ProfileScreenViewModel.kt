package com.immortalidiot.rutlead.presentation.viemodels.main

import androidx.lifecycle.ViewModel
import com.immortalidiot.rutlead.ui.models.LogoutModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileScreenViewModel : ViewModel() {
    private val _logoutUiState = MutableStateFlow(LogoutModel(isDialogVisible = false))
    var logoutUiState: StateFlow<LogoutModel> = _logoutUiState.asStateFlow()

    fun changeLogoutDialogVisibility(isLogoutDialogVisible: Boolean) {
        _logoutUiState.update {
            logoutUiState.value.copy(isDialogVisible = !isLogoutDialogVisible)
        }
    }
}
