package com.example.avitotask.repository.impl

import com.example.avitotask.R
import com.example.avitotask.repository.UserRepository
import com.example.avitotask.retrofit.ErrorResponse
import com.example.avitotask.retrofit.LoginRequest
import com.example.avitotask.retrofit.RegistrationRequest
import com.example.avitotask.retrofit.RetrofitClient
import com.example.avitotask.utils.ResourceProvider
import com.google.gson.Gson

class UserRepositoryImpl(
    private val gson: Gson,
    private val resourceProvider: ResourceProvider
) : UserRepository {
    override suspend fun registerUser(registrationRequest: RegistrationRequest): Result<Unit> {
        return try {
            val response = RetrofitClient.usersApi.registerUser(registrationRequest)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorResponse =
                    gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                Result.failure(
                    Exception(
                        resourceProvider.getStringById(R.string.registration_error) + ": ${errorResponse.message}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loginUser(loginRequest: LoginRequest): Result<String> {
        return try {
            val response = RetrofitClient.usersApi.loginUser(loginRequest)
            if (response.isSuccessful) {
                Result.success(response.body()?.token ?: "")
            } else {
                val errorResponse =
                    gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                Result.failure(
                    Exception(
                        resourceProvider.getStringById(R.string.login_error) + ": ${errorResponse.message}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)

        }
    }

    override suspend fun authProfile(token: String): Result<Unit> {
        return try {
            val response = RetrofitClient.usersApi.getProfile("Bearer $token")
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorResponse =
                    gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                Result.failure(
                    Exception(
                        resourceProvider.getStringById(R.string.auth_error) + ": ${errorResponse.message}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}