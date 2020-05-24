package com.softlabit.mvvmproject.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.softlabit.mvvmproject.coroutine.Coroutines
import com.softlabit.mvvmproject.data.repositories.UserRepository
import com.softlabit.mvvmproject.util.ApiException
import com.softlabit.mvvmproject.util.NoInternetException

class AuthViewModel(
    private var repository: UserRepository
): ViewModel() {
    var name: String? = null
    var phone: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null

    // Interface...
    lateinit var authListener: AuthListener

    // Get Logged in User..
    fun getLoggedInUser() = repository.getUser()

    // Signup Page Navigate..
    fun onNavigateSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    // Signin Page Navigate..
    fun onNavigateSignin(view: View) {
        Intent(view.context, SigninActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    // Login Button Clicked
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
            } catch (e: NoInternetException){
                authListener.onFailure(e.message!!)
            }

        }
    }


    // Signup Button Clicked..
    fun onSignupButtonClick(view: View) {
        authListener.onStarted()

        if (name.isNullOrEmpty()) {
            authListener.onFailure("Name is required!")
            return
        }

        if (phone.isNullOrEmpty()) {
            authListener.onFailure("Phone no is required!")
            return
        }

        if (email.isNullOrEmpty()) {
            authListener.onFailure("Email is required!")
            return
        }

        if (password.isNullOrEmpty()) {
            authListener.onFailure("Please enter a password")
            return
        }

        if (password != passwordConfirm) {
            authListener.onFailure("Password & Confirm Password not matched")
            return
        }

        // With Coroutine..
        Coroutines.main {
            try {
                val authResponse = repository.userSignup(email!!, password!!, name!!, phone!!)

                authResponse.user?.let {
                    authListener.onSuccess(it)
                    // Save User Data to Local Database..
                    repository.saveUser(it)
                    return@main
                }
                authListener.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener.onFailure(e.message!!)
            } catch (e: NoInternetException){
                authListener.onFailure(e.message!!)
            }

        }
    }
}