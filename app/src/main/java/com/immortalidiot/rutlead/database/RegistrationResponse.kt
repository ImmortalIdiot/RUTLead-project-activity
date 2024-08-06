package com.immortalidiot.rutlead.database

data class RegistrationResponse(
    val type: String,
    val title: String,
    val status: Int,
    val errors: Map<String, List<String>>,
    val traceId: String
)
