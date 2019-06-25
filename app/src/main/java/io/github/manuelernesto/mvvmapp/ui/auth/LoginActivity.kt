package io.github.manuelernesto.mvvmapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.manuelernesto.mvvmapp.R
import io.github.manuelernesto.mvvmapp.data.db.entities.User
import io.github.manuelernesto.mvvmapp.databinding.ActivityLoginBinding
import io.github.manuelernesto.mvvmapp.util.hide
import io.github.manuelernesto.mvvmapp.util.show
import io.github.manuelernesto.mvvmapp.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel: AuthViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.viewModel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        progress_bar.show()
        toast("Login Started")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        toast("User Name: ${user.name}")
    }

}
