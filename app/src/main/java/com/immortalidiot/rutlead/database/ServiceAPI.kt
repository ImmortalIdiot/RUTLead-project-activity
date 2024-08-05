package com.immortalidiot.rutlead.database

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceAPI {
    @POST("/student")
    fun register(@Body studentRequest: Student): Call<Student>

    @POST
    fun auth(@Body auth: Student): Call<Token>
}
