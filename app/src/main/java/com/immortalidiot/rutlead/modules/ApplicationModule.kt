package com.immortalidiot.rutlead.modules

import com.immortalidiot.rutlead.database.StudentRepository
import com.immortalidiot.rutlead.database.StudentRepositoryImplementation
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
    fun provideStudentRepository(impl: StudentRepositoryImplementation): StudentRepository
}
