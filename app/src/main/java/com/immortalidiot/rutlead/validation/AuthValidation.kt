package com.immortalidiot.rutlead.validation

import android.util.Patterns
import com.immortalidiot.rutlead.util.Messages.EMPTY_EMAIL
import com.immortalidiot.rutlead.util.Messages.EMPTY_PASSWORD
import com.immortalidiot.rutlead.util.Messages.EMPTY_STUDENT_ID
import com.immortalidiot.rutlead.util.Messages.INCORRECT_FULL_NAME
import com.immortalidiot.rutlead.util.Messages.INCORRECT_GROUP_FORMAT
import com.immortalidiot.rutlead.util.Messages.INCORRECT_LENGTH_PASSWORD
import com.immortalidiot.rutlead.util.Messages.INCORRECT_LENGTH_STUDENT_ID
import com.immortalidiot.rutlead.util.Messages.NOT_EMAIL
import com.immortalidiot.rutlead.util.Messages.NOT_MATCHING_PASSWORDS
import com.immortalidiot.rutlead.util.Messages.ONLY_DIGITS_IN_STUDENT_ID

class AuthValidationException(message: String) : Exception(message)

fun String.validateStudentID(): Result<Unit> {
    val containsOnlyDigits = this.all { char ->
        char.isDigit()
    }

    return if (this.isNotBlank() && containsOnlyDigits && this.length == 8) {
        Result.success(Unit)
    } else if (this.isBlank()) {
        Result.failure(
            AuthValidationException(
                EMPTY_STUDENT_ID
            )
        )
    } else if (!containsOnlyDigits) {
        Result.failure(
            AuthValidationException(
                ONLY_DIGITS_IN_STUDENT_ID
            )
        )
    } else {
        Result.failure(
            AuthValidationException(
                INCORRECT_LENGTH_STUDENT_ID
            )
        )
    }
}

fun String.validatePassword(): Result<Boolean> {
    return if (this.isNotBlank() && this.length >= 8) {
        Result.success(true)
    } else if (isBlank()) {
        Result.failure(
            AuthValidationException(
                EMPTY_PASSWORD
            )
        )
    } else {
        Result.failure(
            AuthValidationException(
                INCORRECT_LENGTH_PASSWORD
            )
        )
    }
}

fun validateConfirmPassword(password: String, confirmPassword: String):Result<Unit> {
    return if (password == confirmPassword) {
        Result.success(Unit)
    } else {
        Result.failure(
            AuthValidationException(
                NOT_MATCHING_PASSWORDS
            )
        )
    }
}

fun String.validateEmail(): Result<Boolean> {
    return if (this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
        Result.success(true)
    } else if (this.isBlank()) {
        Result.failure(
            AuthValidationException(
                EMPTY_EMAIL
            )
        )
    } else {
        Result.failure(
            AuthValidationException(
                NOT_EMAIL
            )
        )
    }
}

fun String.validateGroup(): Result<Boolean> {
    val containsFirstLetters = this.take(3).all { it.isLetter() }
    val containsLastDigits = this.takeLast(3).all { it.isDigit() }

    return if (this.isNotBlank()
        && this.length == 7
        && this[3] == '-'
        && containsFirstLetters
        && containsLastDigits
    ) {
        Result.success(true)
    } else {
        Result.failure(
            AuthValidationException(
                INCORRECT_GROUP_FORMAT
            )
        )
    }
}

fun String.validateName(): Result<Boolean> {
    val fullName = this.split(' ')

    return if (fullName.size == 3 &&
        fullName.all { it.isNotBlank() } &&
        fullName.all { it.all { it.isLetter() } }
    ) {
        Result.success(true)
    } else {
        Result.failure(
            AuthValidationException(
                INCORRECT_FULL_NAME
            )
        )
    }
}
