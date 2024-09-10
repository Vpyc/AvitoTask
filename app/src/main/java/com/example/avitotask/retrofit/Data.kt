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

data class Product(
    val id: String,
    val name: String,
    val price: Int,
    val discounted_price: Int?,
    val images: List<String>
)