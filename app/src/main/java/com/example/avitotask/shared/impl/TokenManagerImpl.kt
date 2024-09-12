package com.example.avitotask.shared.impl

import android.content.Context
import android.content.SharedPreferences
import com.example.avitotask.shared.TokenManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManagerImpl @Inject constructor(
    @ApplicationContext context: Context
) : TokenManager {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val TOKEN_KEY = "auth_token"
    }

    override fun saveToken(token: String?) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, null)
    }
}