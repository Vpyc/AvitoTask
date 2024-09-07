package com.example.avitotask.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class RegistrationViewModel : ValidationViewModel() {
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
        _passwordError.value = value.length > 24
        _confirmPasswordError.value = _confirmPassword.value != value
    }

    fun onConfirmPasswordChange(value: String) {
        _confirmPassword.value = value
        _confirmPasswordError.value = _password.value != value
    }
}