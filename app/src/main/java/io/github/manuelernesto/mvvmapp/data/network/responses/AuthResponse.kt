package io.github.manuelernesto.mvvmapp.data.network.responses

import io.github.manuelernesto.mvvmapp.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)