package io.github.manuelernesto.mvvmapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.manuelernesto.mvvmapp.data.repository.UserRepository

class AuthViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(userRepository) as T
    }
}