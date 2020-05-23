package com.softlabit.mvvmproject.data.repositories

import com.softlabit.mvvmproject.data.model.User
import com.softlabit.mvvmproject.data.network.MyApi
import com.softlabit.mvvmproject.data.network.SafeApiRequest
import com.softlabit.mvvmproject.data.network.responses.AuthResponse

class UserRepository: SafeApiRequest() {
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { MyApi().userLogin(User(email, password)) }
    }
}
