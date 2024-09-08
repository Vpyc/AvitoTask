package com.example.avitotask.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.avitotask.repository.UserRepository

class CommonViewModelFactory : ViewModelProvider.Factory {
    private val userRepository = UserRepository()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> {
                RegistrationViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(userRepository) as T
            }
            else -> throw IllegalArgumentException("Неизвестный класс VM")
        }
    }
}