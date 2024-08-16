package com.immortalidiot.rutlead.database

interface StudentRepository {
    suspend fun registerStudent(student: Student): String
    suspend fun loginStudent(studentId: String, password: String): Result<Unit>
}
