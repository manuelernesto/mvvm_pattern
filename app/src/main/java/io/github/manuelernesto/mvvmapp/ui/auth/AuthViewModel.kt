package io.github.manuelernesto.mvvmapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import io.github.manuelernesto.mvvmapp.data.repository.UserRepository
import io.github.manuelernesto.mvvmapp.util.ApiExeption
import io.github.manuelernesto.mvvmapp.util.Coroutines

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
        Coroutines.main {
            try {
                val authResponse = UserRepository().userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiExeption) {
                authListener?.onFailure(e.message!!)
            }

        }
    }
}