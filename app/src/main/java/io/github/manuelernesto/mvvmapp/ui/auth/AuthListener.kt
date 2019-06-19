package io.github.manuelernesto.mvvmapp.ui.auth

interface AuthListener {
    fun onStarted()
    fun onFailure(message: String)
    fun onSuccess()
}