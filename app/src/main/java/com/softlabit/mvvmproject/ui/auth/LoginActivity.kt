package com.softlabit.mvvmproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.softlabit.mvvmproject.R
import com.softlabit.mvvmproject.databinding.ActivityLoginBinding
import com.softlabit.mvvmproject.ui.auth.AuthListener
import com.softlabit.mvvmproject.ui.auth.AuthViewModel
import com.softlabit.mvvmproject.util.toast

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
        toast("Started")
    }

    override fun onSuccess() {
        toast("Login Success")
    }

    override fun onFailure(message: String) {
        toast(message)
    }

}
