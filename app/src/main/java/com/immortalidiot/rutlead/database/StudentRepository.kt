package com.immortalidiot.rutlead.database

import com.immortalidiot.rutlead.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ServiceAPI::class.java)

    private val unknownErrorMessage = "Неизвестная ошибка, попробуйте позднее"

    suspend fun registerStudent(student: Student): String {
        return withContext(Dispatchers.IO) {
            val response = service.register(student).execute()

            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.status == 201) {
                    "Successful registration"
                } else {
                    responseBody?.errors?.values?.first()?.first() ?: unknownErrorMessage
                }
            } else {
                response.errorBody()?.string() ?: unknownErrorMessage
            }
        }
    }

    suspend fun loginStudent(studentId: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            val response = service.auth(studentId, password).execute()

            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.status == 200) {
                    Result.success(Unit)
                } else {
                    Result.failure(Exception(responseBody?.errors?.values?.first()?.first()
                        ?: unknownErrorMessage))
                }
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: unknownErrorMessage))
            }
        }
    }
}
