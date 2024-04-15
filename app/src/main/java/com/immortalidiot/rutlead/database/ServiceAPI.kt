package com.immortalidiot.rutlead.database

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceAPI {
    @POST("api/RUTLead/student")
    fun register(@Body studentRequest: StudentRequest): Call<StudentRequest>

    @POST
    fun auth(@Body auth: Student): Call<Token>
}

data class StudentRequest(
    val studentID: Int,
    val password: String,
    val email: String,
    val fullName: String,
    val group: String
)

data class StudentResponse(
    val studentID: Int,
    val fullName: String,
)
