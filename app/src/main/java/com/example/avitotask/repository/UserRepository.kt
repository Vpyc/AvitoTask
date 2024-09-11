package com.example.avitotask.repository

import com.example.avitotask.retrofit.LoginRequest
import com.example.avitotask.retrofit.RegistrationRequest

interface UserRepository {
    suspend fun registerUser(registrationRequest: RegistrationRequest): Result<Unit>

    suspend fun loginUser(loginRequest: LoginRequest): Result<String>

    suspend fun authProfile(token: String): Result<Unit>
}