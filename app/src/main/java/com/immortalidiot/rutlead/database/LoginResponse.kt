package com.immortalidiot.rutlead.database

data class LoginResponse (
    val status: Int,
    val errors: Map<String, List<String>>,
    val fullName: String,
    val group: String
)
