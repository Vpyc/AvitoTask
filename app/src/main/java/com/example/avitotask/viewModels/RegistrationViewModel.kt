package com.example.avitotask.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.avitotask.repository.UserRepository
import com.example.avitotask.retrofit.RegistrationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ValidationViewModel() {
    private val _name = mutableStateOf("")
    val name: MutableState<String> = _name

    private val _confirmPassword = mutableStateOf("")
    val confirmPassword: MutableState<String> = _confirmPassword

    private val _confirmPasswordError = mutableStateOf(false)
    val confirmPasswordError: MutableState<Boolean> = _confirmPasswordError

    fun onNameChange(value: String) {
        _name.value = value
    }

    override fun onPasswordChange(value: String) {
        _password.value = value
        _passwordError.value = value.length > 24 || value.length < 8
        _confirmPasswordError.value = _confirmPassword.value != value
    }

    fun onConfirmPasswordChange(value: String) {
        _confirmPassword.value = value
        _confirmPasswordError.value = _password.value != value
    }

    fun register(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val request = RegistrationRequest(
                name = name.value,
                email = email.value,
                password = password.value,
                cpassword = confirmPassword.value
            )

            val result = userRepository.registerUser(request)
            if (result.isSuccess) {
                onSuccess()
            } else {
                onError(result.exceptionOrNull()?.message ?: "Неизвестная ошибка")
            }
        }
    }
}