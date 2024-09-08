package com.example.avitotask.retrofit

data class RegistrationRequest(
    val name: String,
    val email: String,
    val password: String,
    val cpassword: String
)

data class ErrorResponse(
    val status: String,
    val message: String
)