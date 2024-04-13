package com.immortalidiot.rutlead.database

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientAPI {
    private const val url = "" //TODO(): add the server url
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}
