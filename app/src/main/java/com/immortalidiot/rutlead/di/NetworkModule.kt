package com.immortalidiot.rutlead.di

import com.immortalidiot.rutlead.BuildConfig
import com.immortalidiot.rutlead.database.ServiceAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideServiceAPI(retrofit: Retrofit): ServiceAPI = retrofit.create(ServiceAPI::class.java)
}
