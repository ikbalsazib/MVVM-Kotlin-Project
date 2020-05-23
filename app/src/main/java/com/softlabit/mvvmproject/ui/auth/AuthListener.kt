package com.softlabit.mvvmproject.ui.auth

import androidx.lifecycle.LiveData
import com.softlabit.mvvmproject.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}