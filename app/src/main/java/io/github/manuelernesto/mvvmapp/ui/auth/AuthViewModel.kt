package io.github.manuelernesto.mvvmapp.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import io.github.manuelernesto.mvvmapp.data.network.MyAPI
import io.github.manuelernesto.mvvmapp.data.repository.UserRepository
import io.github.manuelernesto.mvvmapp.util.ApiExeption
import io.github.manuelernesto.mvvmapp.util.Coroutines
import io.github.manuelernesto.mvvmapp.util.NoInternetExeption

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Campos em brancos nao permitidos")
            return
        }

        //success
        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiExeption) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetExeption) {
                authListener?.onFailure(e.message!!)
            }

        }
    }

    fun onLogin(view: View) {
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignUp(view: View) {
        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view: View) {
        authListener?.onStarted()

        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Name is required")
            return
        }
        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Email is required")
            return
        }
        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Please enter the password")
            return
        }
        if (password != passwordConfirm) {
            authListener?.onFailure("Password did not match")
            return
        }

        //success
        Coroutines.main {
            try {
                val authResponse = repository.userSignup(name!!, email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiExeption) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetExeption) {
                authListener?.onFailure(e.message!!)
            }

        }
    }
}