package com.example.avitotask.viewModels

import androidx.lifecycle.viewModelScope
import com.example.avitotask.R
import com.example.avitotask.repository.UserRepository
import com.example.avitotask.retrofit.LoginRequest
import com.example.avitotask.shared.TokenManager
import com.example.avitotask.utils.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val resourceProvider: ResourceProvider,
    private val tokenManager: TokenManager
) : ValidationViewModel() {

    fun validateToken(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val token = tokenManager.getToken()
            try {
                if (token != null) {
                    val result = userRepository.authProfile(token)
                    onResult(result.isSuccess)
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                onResult(false)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun login(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _errorMessage.value = null
            val request = LoginRequest(
                email = email.value,
                password = password.value,
            )
            val result = userRepository.loginUser(request)
            if (result.isSuccess) {
                tokenManager.saveToken((result.getOrNull()))
                onSuccess()
            } else {
                _errorMessage.value =
                    result.exceptionOrNull()?.localizedMessage
                        ?: resourceProvider.getStringById(R.string.unknown_error)
                onError(_errorMessage.value!!)
            }
        }
    }
}