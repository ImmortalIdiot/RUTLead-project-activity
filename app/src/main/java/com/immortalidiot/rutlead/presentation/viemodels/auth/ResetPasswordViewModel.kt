package com.immortalidiot.rutlead.presentation.viemodels.auth

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.immortalidiot.rutlead.ui.models.ResetPasswordModel
import com.immortalidiot.rutlead.validation.validateConfirmPassword
import com.immortalidiot.rutlead.validation.validateEmail
import com.immortalidiot.rutlead.validation.validatePassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ResetPasswordViewModel : ViewModel() {
    @Immutable
    sealed class State {
        object Init : State()
        object Success : State()
        data class Error(val message: String) : State()
        data class ValidationError(
            val emailError: String?,
            val passwordError: String?,
            val confirmPasswordError: String?
        ) : State()
    }

    var mutableState = MutableStateFlow<State>(State.Init)
        private set

    private val _uiState = MutableStateFlow(
        ResetPasswordModel(
            email = String(),
            password = String(),
            confirmPassword = String(),
            isFocused = false,
            isPasswordVisible = true
        )
    )

    val uiState: StateFlow<ResetPasswordModel> = _uiState.asStateFlow()

    fun clearErrorStack() {
        mutableState.update {
            State.Init
        }
    }

    fun changeEmail(email: String) {
        _uiState.update {
            uiState.value.copy(email = email)
        }
    }

    fun changePassword(password: String) {
        _uiState.update {
            uiState.value.copy(password = password)
        }
    }

    fun changeConfirmPassword(confirmPassword: String) {
        _uiState.update {
            uiState.value.copy(confirmPassword = confirmPassword)
        }
    }

    fun changePasswordVisibility(isVisible: Boolean) {
        _uiState.update {
            uiState.value.copy(isPasswordVisible = !isVisible)
        }
    }

    fun resetPassword() {
        val email = _uiState.value.email.validateEmail()
        val password = _uiState.value.password.validatePassword()
        val confirmPassword = validateConfirmPassword(
            password = uiState.value.password,
            confirmPassword = uiState.value.confirmPassword
        )

        if (email.isFailure || password.isFailure || confirmPassword.isFailure) {
            mutableState.update {
                State.ValidationError(
                    emailError = email.exceptionOrNull()?.message,
                    passwordError = password.exceptionOrNull()?.message,
                    confirmPasswordError = confirmPassword.exceptionOrNull()?.message
                )
            }
        } else {
            mutableState.value = State.Success
            // TODO: reset password
        }
    }
}
