package com.immortalidiot.rutlead.database

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceAPI {
    @POST("/student")
    suspend fun register(@Body newStudent: Student): Call<RegistrationResponse>

    @POST
    suspend fun auth(@Body studentId: Int, @Body password: String): Call<LoginResponse> // TODO: add token
}
