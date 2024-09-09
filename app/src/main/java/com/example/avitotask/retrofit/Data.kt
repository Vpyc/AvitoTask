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

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val status: String,
    val token: String
)