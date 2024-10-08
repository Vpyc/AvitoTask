package com.example.avitotask.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * Базовый [ViewModel], используемый для хранения состояния загрузки
 */
open class BaseViewModel : ViewModel() {
    /**
     * Состояние загрузки
     */
    protected val _isLoading = mutableStateOf(false)
    val isLoading: MutableState<Boolean> = _isLoading

    /**
     * Сообщение об ошибке
     */
    protected val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: MutableState<String?> = _errorMessage
}