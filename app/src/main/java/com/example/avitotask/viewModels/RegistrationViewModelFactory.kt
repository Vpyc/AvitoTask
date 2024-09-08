package com.example.avitotask.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.avitotask.repository.UserRepository

class RegistrationViewModelFactory: ViewModelProvider.Factory {
    private val userRepository = UserRepository()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrationViewModel(userRepository) as T
    }
}