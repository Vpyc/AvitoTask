package com.example.avitotask.shared

interface TokenManager {
    fun saveToken(token: String?)

    fun getToken(): String?
}