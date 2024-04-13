package com.immortalidiot.rutlead.database

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceAPI {
    @POST("register")
    fun register(@Body register: Student): Call<Student>

    @POST
    fun auth(@Body auth: Student): Call<Token>
}
