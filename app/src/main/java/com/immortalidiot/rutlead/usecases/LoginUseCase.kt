package com.immortalidiot.rutlead.usecases

import com.immortalidiot.rutlead.database.StudentRepository
import com.immortalidiot.rutlead.di.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LoginUseCase {
    suspend fun loginStudent(studentId: String, password: String): Result<Unit>
}

class LoginUseCaseImpl @Inject constructor(
    private val studentRepository: StudentRepository,
    @Dispatcher(Dispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : LoginUseCase {
    override suspend fun loginStudent(studentId: String, password: String): Result<Unit> {
        return withContext(ioDispatcher) {
            studentRepository.loginStudent(studentId = studentId, password = password)
        }
    }
}
