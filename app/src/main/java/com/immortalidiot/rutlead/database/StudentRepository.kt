package com.immortalidiot.rutlead.database

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.186.170:5036/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ServiceAPI::class.java)

    fun registerUser(student: StudentRequest) {
        service.register(student).enqueue(object : Callback<StudentRequest> {
                override fun onResponse(
                    call: Call<StudentRequest>,
                    response: Response<StudentRequest>
                ) {
                    //TODO(): realize a success output
                }
                override fun onFailure(call: Call<StudentRequest>, t: Throwable) {
                    //TODO(): realize a fail output
                }
            }
        )
    }
}
