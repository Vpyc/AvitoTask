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

    var token: String?  = null
    fun login(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val request = LoginRequest(
                email = email.value,
                password = password.value,
            )
            val result = userRepository.loginUser(request)
            if (result.isSuccess) {
                token = result.getOrNull()
                onSuccess()
            } else {
                onError(result.exceptionOrNull()?.message ?: "Неизвестная ошибка")
            }
        }
    }
}