package com.softlabit.mvvmproject.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.softlabit.mvvmproject.coroutine.Coroutines
import com.softlabit.mvvmproject.data.repositories.UserRepository
import com.softlabit.mvvmproject.util.ApiException

class AuthViewModel(
    private var repository: UserRepository
): ViewModel() {
    var email: String? = null
    var password: String? = null

    // Interface...
    lateinit var authListener: AuthListener

    // Get Logged in User..
    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener.onFailure("Invalid email or password!")
            return
        }

        // With Coroutine..
        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)

                authResponse.user?.let {
                    authListener.onSuccess(it)
                    // Save User Data to Local Database..
                    repository.saveUser(it)
                    return@main
                }
                authListener.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener.onFailure(e.message!!)
            }

        }
    }
}