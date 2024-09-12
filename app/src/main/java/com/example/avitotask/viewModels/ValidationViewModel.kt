package com.example.avitotask.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * Наследуемый ViewModel для валидации данных. Основан на [BaseViewModel].
 */
open class ValidationViewModel : BaseViewModel() {
    /**
     * Значение поля [email]
     */
    protected val _email = mutableStateOf("")
    val email: MutableState<String> = _email

    /**
     * Значение поля [password]
     */
    protected val _password = mutableStateOf("")
    val password: MutableState<String> = _password

    /**
     * Состояние ошибки поля [email]
     */
    protected val _emailError = mutableStateOf(false)
    val emailError: MutableState<Boolean> = _emailError

    /**
     * Состояние ошибки поля [password]
     */
    protected val _passwordError = mutableStateOf(false)
    val passwordError: MutableState<Boolean> = _passwordError

    /**
     * Функция, которая проверяет валидность email. Используется для TextField
     * @param value значение TextField
     */
    fun onEmailChange(value: String) {
        _email.value = value
        _emailError.value = !isValidEmail(value)
    }

    /**
     * Функция, которая проверяет валидность password. Используется для TextField
     * @param value значение TextField
     */
    open fun onPasswordChange(value: String) {
        _password.value = value
        _passwordError.value = value.length > 24 || value.length < 8
    }

    /**
     * Валидация email
     * @param email значение TextField
     */
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}