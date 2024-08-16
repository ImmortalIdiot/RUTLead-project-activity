package com.immortalidiot.rutlead.modules

import com.immortalidiot.rutlead.database.StudentRepository
import com.immortalidiot.rutlead.database.StudentRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun provideStudentRepository(): StudentRepository { return StudentRepositoryImplementation() }
}
