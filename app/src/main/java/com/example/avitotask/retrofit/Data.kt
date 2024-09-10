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

data class ProductList(
    val _id: String,
    val name: String,
    val price: Int,
    val discounted_price: Int?,
    val images: List<String>,
)

data class ProductsResponse(
    val status: String,
    val count: Int,
    val Data: List<ProductList>
)

data class Product(
    val name: String,
    val price: Int,
    val discounted_price: Int?,
    val images: List<String>,
    val description: String,
    val product_specifications: List<Specification>,
)

data class Specification(
    val key: String,
    val value: String)

data class ProductByIdResponse(
    val status: String,
    val data: Product
)