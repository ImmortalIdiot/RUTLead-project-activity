package com.immortalidiot.rutlead.di

import com.immortalidiot.rutlead.usecases.LoginUseCase
import com.immortalidiot.rutlead.usecases.LoginUseCaseImpl
import com.immortalidiot.rutlead.usecases.RegistrationUseCase
import com.immortalidiot.rutlead.usecases.RegistrationUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthUseCaseModule {

    @Binds
    @Singleton
    fun provideLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase
    
    @Binds
    @Singleton
    fun provideRegistrationUseCase(impl: RegistrationUseCaseImpl): RegistrationUseCase
}
