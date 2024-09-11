package com.example.avitotask.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

open class BaseViewModel() : ViewModel() {
    protected val _isLoading = mutableStateOf(false)
    val isLoading: MutableState<Boolean> = _isLoading
}