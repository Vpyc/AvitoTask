package com.example.avitotask.shared

/**
 * Менеджер для сохранения токена и получения его из внутреннего хранилища
 */
interface TokenManager {
    /**
     * Метод для сохранения токена во внутреннее хранилище
     * @param token Значение токена
     */
    fun saveToken(token: String?)

    /**
     * Метод для получения токена из внутреннего хранилища
     * @return Значение токена
     */
    fun getToken(): String?
}