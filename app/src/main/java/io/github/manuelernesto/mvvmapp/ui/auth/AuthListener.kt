package io.github.manuelernesto.mvvmapp.ui.auth

import io.github.manuelernesto.mvvmapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onFailure(message: String)
    fun onSuccess(user: User)
}