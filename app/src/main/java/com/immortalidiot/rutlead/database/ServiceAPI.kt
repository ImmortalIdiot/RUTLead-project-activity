package com.immortalidiot.rutlead.database

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceAPI {
    @POST("/student")
    suspend fun register(@Body newStudent: Student): Response<Unit>

    @POST
    fun auth(@Body auth: Student): Call<Token>
}
