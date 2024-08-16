package com.immortalidiot.rutlead.di

import com.immortalidiot.rutlead.usecases.LoginUseCase
import com.immortalidiot.rutlead.usecases.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LoginUseCaseModule {
    @Binds
    @Singleton
    fun provideLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase
}
