package com.immortalidiot.rutlead.database

import com.immortalidiot.rutlead.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ServiceAPI::class.java)

    fun registerUser(student: Student) {
        service.register(student).enqueue(object : Callback<Student> {
                override fun onResponse(
                    call: Call<Student>,
                    response: Response<Student>
                ) {
                    //TODO(): realize a success output
                }
                override fun onFailure(call: Call<Student>, t: Throwable) {
                    //TODO(): realize a fail output
                }
            }
        )
    }
}
