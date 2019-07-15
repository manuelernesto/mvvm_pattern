package io.github.manuelernesto.mvvmapp.ui.home.profile

import androidx.lifecycle.ViewModel
import io.github.manuelernesto.mvvmapp.data.repository.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()

}
