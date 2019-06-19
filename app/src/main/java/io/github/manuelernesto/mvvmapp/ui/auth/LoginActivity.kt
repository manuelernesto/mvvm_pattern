package io.github.manuelernesto.mvvmapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import io.github.manuelernesto.mvvmapp.R
import io.github.manuelernesto.mvvmapp.databinding.ActivityLoginBinding
import io.github.manuelernesto.mvvmapp.util.toast

class LoginActivity : AppCompatActivity(), AuthListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel= ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.viewModel = viewModel
        viewModel.authListener= this
    }

    override fun onStarted() {
        toast("Login Started")
    }

    override fun onFailure(message: String) {
        toast(message)
    }

    override fun onSuccess() {
        toast("Login Success")
    }

}
