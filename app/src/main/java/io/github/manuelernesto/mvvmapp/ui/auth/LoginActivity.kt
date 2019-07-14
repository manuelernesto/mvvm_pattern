package io.github.manuelernesto.mvvmapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.manuelernesto.mvvmapp.R
import io.github.manuelernesto.mvvmapp.data.db.entities.User
import io.github.manuelernesto.mvvmapp.databinding.ActivityLoginBinding
import io.github.manuelernesto.mvvmapp.ui.home.HomeActivity
import io.github.manuelernesto.mvvmapp.util.hide
import io.github.manuelernesto.mvvmapp.util.show
import io.github.manuelernesto.mvvmapp.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {
//        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
//        val api = MyAPI(networkConnectionInterceptor)
//        val db = AppDatabase(this)
//        val repository = UserRepository(api, db)
//        val factory = AuthViewModelFactory(repository)

    override val kodein by kodein()
    private val factory: AuthViewModelFactory  by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel: AuthViewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewModel = viewModel
        viewModel.authListener = this


        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null)
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

        })
    }

    override fun onStarted() {
        progress_bar.show()
        // toast("Login Started")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
//        toast(message)
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
//        root_layout.snackbar("User Name: ${user.name}")
//        toast("User Name: ${user.name}")
    }

}
