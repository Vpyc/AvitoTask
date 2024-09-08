package com.example.avitotask.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UsersApi {
    @POST("users")
    suspend fun registerUser(@Body registrationRequest: RegistrationRequest)
    : Response<Unit>

    @POST("users/auth/login")
    suspend fun loginUser(@Body authRequest: LoginRequest): Response<LoginResponse>

    @GET("users/auth/profile")
    suspend fun getProfile(@Header("Authorization") token: String): Response<Unit>
}