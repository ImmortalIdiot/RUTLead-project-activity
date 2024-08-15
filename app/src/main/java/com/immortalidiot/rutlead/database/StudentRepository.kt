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

    suspend fun registerUser(student: Student): String {
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
}
