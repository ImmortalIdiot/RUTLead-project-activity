package com.immortalidiot.rutlead.usecases

import com.immortalidiot.rutlead.database.Student
import com.immortalidiot.rutlead.database.StudentRepository
import com.immortalidiot.rutlead.di.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RegistrationUseCase {
    suspend fun registerStudent(student: Student): String
}

class RegistrationUseCaseImpl @Inject constructor(
    private val studentRepository: StudentRepository,
    @Dispatcher(Dispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : RegistrationUseCase {

    override suspend fun registerStudent(student: Student): String {
        return withContext(ioDispatcher) {
            studentRepository.registerStudent(student = student)
        }
    }
}
