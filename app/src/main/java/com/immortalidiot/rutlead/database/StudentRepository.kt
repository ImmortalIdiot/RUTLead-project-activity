package com.immortalidiot.rutlead.database

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5036/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ServiceAPI::class.java)

    fun registerUser(student: StudentRequest) {
        val call = service.register(student)
        call.enqueue(object : Callback<StudentRequest> {
            override fun onResponse(
                call: Call<StudentRequest>,
                response: Response<StudentRequest>
            ) {
                if (response.isSuccessful) {
                    response.body()
                } else {
                    //TODO(): add error output
                }
            }

            override fun onFailure(call: Call<StudentRequest>, t: Throwable) {
                //TODO(): add error output
            }
        })
    }
}
