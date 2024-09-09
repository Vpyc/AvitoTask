package com.example.avitotask.viewModels

import androidx.lifecycle.ViewModel
import com.example.avitotask.shared.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val tokenManager: TokenManager
): ViewModel() {
    fun getToken() = tokenManager.getToken()
}