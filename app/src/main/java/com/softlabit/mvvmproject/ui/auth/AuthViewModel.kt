package com.softlabit.mvvmproject.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.softlabit.mvvmproject.coroutine.Coroutines
import com.softlabit.mvvmproject.data.repositories.UserRepository

class AuthViewModel: ViewModel() {
    var email: String? = null
    var password: String? = null

    // Interface...
    lateinit var authListener: AuthListener

    fun onLoginButtonClick(view: View) {
        authListener.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener.onFailure("Invalid email or password!")
            return
        }

        // With Coroutine..
        Coroutines.main {
            val response = UserRepository().userLogin(email!!, password!!)

            if (response.isSuccessful) {
                authListener.onSuccess(response.body()?.user!!)
            } else {
                authListener.onFailure("Error code: ${response.code()}")
            }
        }
    }
}