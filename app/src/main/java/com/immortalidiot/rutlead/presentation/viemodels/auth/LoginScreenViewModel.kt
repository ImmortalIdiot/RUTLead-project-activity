package com.immortalidiot.rutlead.presentation.viemodels.auth

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immortalidiot.rutlead.database.StudentRepository
import com.immortalidiot.rutlead.ui.models.LoginModel
import com.immortalidiot.rutlead.validation.validatePassword
import com.immortalidiot.rutlead.validation.validateStudentID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {
    @Immutable
    sealed class State {
        object Init : State()
        object Success : State()
        data class Error(val message: String) : State()
        data class ValidationError(
            val studentIDError: String?,
            val passwordError: String?
        ) : State()
    }

    var mutableState = MutableStateFlow<State>(State.Init)
        private set

    private val _uiState = MutableStateFlow(
        LoginModel(
            studentID = String(),
            password = String(),
            isPasswordVisible = true
        )
    )

    val uiState: StateFlow<LoginModel> = _uiState.asStateFlow()

    fun clearErrorStack() {
        mutableState.update {
            State.Init
        }
    }

    fun changeLogin(studentID: String) {
        _uiState.update {
            uiState.value.copy(studentID = studentID)
        }
    }

    fun changePassword(password: String) {
        _uiState.update {
            uiState.value.copy(password = password)
        }
    }

    fun changePasswordVisibility(isPasswordVisible: Boolean) {
        _uiState.update {
            uiState.value.copy(isPasswordVisible = !isPasswordVisible)
        }
    }

    fun login() {
        val studentID = _uiState.value.studentID.validateStudentID()
        val password = _uiState.value.password.validatePassword()

        if (studentID.isFailure || password.isFailure) {
            mutableState.update {
                State.ValidationError(
                    studentIDError = studentID.exceptionOrNull()?.message,
                    passwordError = password.exceptionOrNull()?.message
                )
            }
        } else {
            viewModelScope.launch {
                handleResult(
                    studentRepository.loginStudent(
                        _uiState.value.studentID,
                        _uiState.value.password
                    )
                )
            }
        }
    }

    private fun handleResult(result: Result<Unit>) {
        if (result.isSuccess) {
            mutableState.update { State.Success }
        } else {
            mutableState.update {
                val errorMessage = result.exceptionOrNull()?.message ?: "Неизвестная ошибка"
                State.Error(errorMessage)
            }
        }
    }
}
