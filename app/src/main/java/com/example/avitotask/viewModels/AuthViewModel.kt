package com.example.avitotask.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.avitotask.repository.UserRepository
import com.example.avitotask.retrofit.LoginRequest
import com.example.avitotask.retrofit.RegistrationRequest
import kotlinx.coroutines.launch

class AuthViewModel(private val userRepository: UserRepository) : ValidationViewModel() {

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