package com.example.avitotask.repository

import com.example.avitotask.retrofit.LoginRequest
import com.example.avitotask.retrofit.RegistrationRequest

/**
 * Интерфейс для взаимодействия с API для регистрации и авторизации пользователя
 */
interface UserRepository {
    /**
     * Регистрация пользователя
     * @param registrationRequest Данные для регистрации пользователя
     * @return Результат регистрации пользователя
     */
    suspend fun registerUser(registrationRequest: RegistrationRequest): Result<Unit>

    /**
     * Вход пользователя
     * @param loginRequest Данные для входа пользователя
     * @return Токен для последующей авторизации пользователя
     */
    suspend fun loginUser(loginRequest: LoginRequest): Result<String>

    /**
     * Регистрация пользователя
     * @param token Токен для авторизации пользователя
     * @return Результат регистрации пользователя
     */
    suspend fun authProfile(token: String): Result<Unit>
}