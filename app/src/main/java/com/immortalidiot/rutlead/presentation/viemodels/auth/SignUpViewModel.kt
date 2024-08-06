package com.immortalidiot.rutlead.presentation.viemodels.auth

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immortalidiot.rutlead.database.Student
import com.immortalidiot.rutlead.database.StudentRepository
import com.immortalidiot.rutlead.ui.models.SignUpModel
import com.immortalidiot.rutlead.validation.validateEmail
import com.immortalidiot.rutlead.validation.validateGroup
import com.immortalidiot.rutlead.validation.validateName
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
class SignUpViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {
    @Immutable
    sealed class State {
        object Init : State()
        object Success : State()
        object Loading : State()
        object SecondPart : State()
        data class Error(val message: String) : State()
        data class SignUpValidationFirstPartError(
            val studentIDError: String?,
            val emailError: String?,
            val passwordError: String?,
        ) : State()
        data class SignUpValidationSecondPartError(
            val groupError: String?,
            val nameError: String?
        ) : State()
    }

    var mutableState = MutableStateFlow<State>(State.Init)
        private set

    private val _uiState = MutableStateFlow(
        SignUpModel(
            email = String(),
            studentID = String(),
            password = String(),
            group = String(),
            name = String(),
            isFocused = false,
            isPasswordVisible = true
        )
    )

    val uiState: StateFlow<SignUpModel> = _uiState.asStateFlow()

    fun clearErrorStackInFirstPart() {
        mutableState.update {
            State.Init
        }
    }

    fun clearErrorStackInSecondPart() {
        mutableState.update {
            State.SecondPart
        }
    }

    fun changeEmail(email: String) {
        _uiState.update {
            uiState.value.copy(email = email)
        }
    }

    fun changeStudentID(studentID: String) {
        _uiState.update {
            uiState.value.copy(studentID = studentID)
        }
    }

    fun changePassword(password: String) {
        _uiState.update {
            uiState.value.copy(password = password)
        }
    }

    fun changeGroup(group: String) {
        _uiState.update {
            uiState.value.copy(group = group)
        }
    }

    fun changeName(name: String) {
        _uiState.update {
            uiState.value.copy(name = name)
        }
    }

    fun changePasswordVisibility(isVisible: Boolean) {
        _uiState.update {
            uiState.value.copy(isPasswordVisible = !isVisible)
        }
    }

    fun updateScreen() {
        val email = _uiState.value.email.validateEmail()
        val studentID = _uiState.value.studentID.validateStudentID()
        val password = _uiState.value.password.validatePassword()

        if (email.isFailure || studentID.isFailure || password.isFailure) {
            mutableState.update {
                State.SignUpValidationFirstPartError(
                    emailError = email.exceptionOrNull()?.message,
                    studentIDError = studentID.exceptionOrNull()?.message,
                    passwordError = password.exceptionOrNull()?.message,
                )
            }
        } else {
            mutableState.value = State.SecondPart
        }
    }

    fun register() {
        val group = _uiState.value.group.validateGroup()
        val name = _uiState.value.name.validateName()

        if (group.isFailure || name.isFailure) {
            mutableState.update {
                State.SignUpValidationSecondPartError(
                    groupError = group.exceptionOrNull()?.message,
                    nameError = name.exceptionOrNull()?.message
                )
            }
        } else {
            viewModelScope.launch {
                val student = Student(
                    studentID = _uiState.value.studentID.toInt(),
                    password = _uiState.value.password,
                    email = _uiState.value.email,
                    group = _uiState.value.group,
                    fullName = _uiState.value.name
                )
                studentRepository.registerUser(student)
            }
            mutableState.update {
                State.Success
            }
            // TODO: register the user
        }
    }
}
