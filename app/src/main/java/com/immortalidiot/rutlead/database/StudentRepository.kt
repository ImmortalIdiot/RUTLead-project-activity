package com.immortalidiot.rutlead.database

import com.google.gson.Gson
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val service: ServiceAPI
) {
    suspend fun registerUser(student: Student): Result<Unit> {
        val response = service.register(student)
        return if (response.isSuccessful) {
            Result.success(Unit)
        } else {
            val error = response.errorBody()?.string()
            val errorResponse = error?.let { parseResponse(it) }
            Result.failure(Exception(errorResponse?.title ?: "Попробуйте позднее"))
        }
    }

    private fun parseResponse(json: String): RegistrationResponse? {
        val gson = Gson()
        return gson.fromJson(json, RegistrationResponse::class.java)
    }
}
