package com.example.avitotask.viewModels

import androidx.lifecycle.viewModelScope
import com.example.avitotask.repository.UserRepository
import com.example.avitotask.retrofit.LoginRequest
import com.example.avitotask.shared.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager
) : ValidationViewModel() {

    fun validateToken(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val token = tokenManager.getToken()
            try {
                if (token != null) {
                    val result = userRepository.authProfile(token)
                    _isLoading.value = false
                    onResult(result.isSuccess)
                } else {
                    _isLoading.value = false
                    onResult(false)
                }
            } catch (e: Exception) {
                _isLoading.value = false
                onResult(false)
            }
        }
    }

    fun login(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val request = LoginRequest(
                email = email.value,
                password = password.value,
            )
            val result = userRepository.loginUser(request)
            if (result.isSuccess) {
                tokenManager.saveToken((result.getOrNull()))
                onSuccess()
            } else {
                onError(result.exceptionOrNull()?.message ?: "Неизвестная ошибка")
            }
        }
    }
}