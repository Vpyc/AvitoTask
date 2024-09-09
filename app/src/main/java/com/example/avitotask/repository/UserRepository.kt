package com.example.avitotask.repository

import com.example.avitotask.retrofit.ErrorResponse
import com.example.avitotask.retrofit.LoginRequest
import com.example.avitotask.retrofit.RegistrationRequest
import com.example.avitotask.retrofit.RetrofitClient
import com.google.gson.Gson

class UserRepository {

    private val gson = Gson()
    suspend fun registerUser(registrationRequest: RegistrationRequest): Result<Unit> {
        return try {
            val response = RetrofitClient.usersApi.registerUser(registrationRequest)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorResponse =
                    gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                Result.failure(Exception("Ошибка при регистрации: ${errorResponse.message}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginUser(loginRequest: LoginRequest): Result<String> {
        return try {
            val response = RetrofitClient.usersApi.loginUser(loginRequest)
            if (response.isSuccessful) {
                Result.success(response.body()?.token ?: "")
            } else {
                val errorResponse =
                    gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                Result.failure(Exception("Ошибка при входе: ${errorResponse.message}"))
            }
        } catch (e: Exception) {
            Result.failure(e)

        }
    }

    suspend fun getProfile(token: String): Result<Unit> {
        return try {
            val response = RetrofitClient.usersApi.getProfile("Bearer $token")
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorResponse =
                    gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                Result.failure(Exception("Ошибка при получении профиля: ${errorResponse.message}"))
            }
        } catch (e: Exception) {
            Result.failure(e)

        }
    }
}