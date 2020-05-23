package com.softlabit.mvvmproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.softlabit.mvvmproject.R
import com.softlabit.mvvmproject.data.db.entities.User
import com.softlabit.mvvmproject.databinding.ActivityLoginBinding
import com.softlabit.mvvmproject.ui.auth.AuthListener
import com.softlabit.mvvmproject.ui.auth.AuthViewModel
import com.softlabit.mvvmproject.util.hide
import com.softlabit.mvvmproject.util.show
import com.softlabit.mvvmproject.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Normal Layout Binding..
        // setContentView(R.layout.activity_login)

        // Data Binding with View Model...
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        // Interface Initialize..
        viewModel.authListener = this
    }


    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        toast("${user.name} is logged in.")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
    }

}
