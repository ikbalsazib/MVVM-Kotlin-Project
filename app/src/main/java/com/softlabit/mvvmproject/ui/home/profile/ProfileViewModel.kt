package com.softlabit.mvvmproject.ui.home.profile

import androidx.lifecycle.ViewModel
import com.softlabit.mvvmproject.data.repositories.UserRepository

class ProfileViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()
}
