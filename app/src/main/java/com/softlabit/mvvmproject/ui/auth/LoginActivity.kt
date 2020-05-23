package com.softlabit.mvvmproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.softlabit.mvvmproject.R
import com.softlabit.mvvmproject.data.db.AppDatabase
import com.softlabit.mvvmproject.data.db.entities.User
import com.softlabit.mvvmproject.data.network.MyApi
import com.softlabit.mvvmproject.data.repositories.UserRepository
import com.softlabit.mvvmproject.databinding.ActivityLoginBinding
import com.softlabit.mvvmproject.ui.auth.AuthListener
import com.softlabit.mvvmproject.ui.auth.AuthViewModel
import com.softlabit.mvvmproject.ui.auth.AuthViewModelFactory
import com.softlabit.mvvmproject.ui.home.HomeActivity
import com.softlabit.mvvmproject.util.hide
import com.softlabit.mvvmproject.util.show
import com.softlabit.mvvmproject.util.snackbar
import com.softlabit.mvvmproject.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Normal Layout Binding..
        // setContentView(R.layout.activity_login)

        // Instances..
        val api = MyApi()
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        // Data Binding with View Model...
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
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
