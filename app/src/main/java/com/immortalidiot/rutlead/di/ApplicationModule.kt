package com.immortalidiot.rutlead.di

import com.immortalidiot.rutlead.database.StudentRepository
import com.immortalidiot.rutlead.database.StudentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationModule {
    @Binds
    @Singleton
    fun provideStudentRepository(impl: StudentRepositoryImpl): StudentRepository
}
