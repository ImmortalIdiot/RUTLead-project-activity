package com.immortalidiot.rutlead.database

import com.google.gson.Gson
import com.immortalidiot.rutlead.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ServiceAPI::class.java)

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
