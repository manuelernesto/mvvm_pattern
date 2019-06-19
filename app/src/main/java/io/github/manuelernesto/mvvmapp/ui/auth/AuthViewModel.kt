package io.github.manuelernesto.mvvmapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import io.github.manuelernesto.mvvmapp.data.repository.UserRepository

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            //display message
            authListener?.onFailure("Campos em brancos nao permitidos")
            return
        }

        //success
        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginResponse)
    }
}