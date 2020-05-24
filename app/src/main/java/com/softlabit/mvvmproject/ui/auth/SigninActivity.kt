package com.softlabit.mvvmproject.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.softlabit.mvvmproject.R
import com.softlabit.mvvmproject.data.db.entities.User
import com.softlabit.mvvmproject.databinding.ActivitySigninBinding
import com.softlabit.mvvmproject.ui.home.HomeActivity
import com.softlabit.mvvmproject.util.hide
import com.softlabit.mvvmproject.util.show
import com.softlabit.mvvmproject.util.snackbar
import kotlinx.android.synthetic.main.activity_signin.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SigninActivity : AppCompatActivity(), AuthListener, KodeinAware {
    // Dependency Injector..
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_signin)

        // Data Binding with View Model...
        val binding: ActivitySigninBinding = DataBindingUtil.setContentView(this, R.layout.activity_signin)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        // Interface Initialize..
        viewModel.authListener = this

        // Check User is Logged in or not!
        viewModel.getLoggedInUser().observe(this, Observer {user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    // Clear All Activity..
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }


    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackbar("${user.name} is logged in.")
        // toast("${user.name} is logged in.")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
        // toast(message)
    }
}
