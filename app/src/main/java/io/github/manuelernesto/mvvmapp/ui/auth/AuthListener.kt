package io.github.manuelernesto.mvvmapp.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {
    fun onStarted()
    fun onFailure(message: String)
    fun onSuccess(loginResponse: LiveData<String>)
}