package com.softlabit.mvvmproject.data.repositories

import com.softlabit.mvvmproject.data.db.AppDatabase
import com.softlabit.mvvmproject.data.model.User
import com.softlabit.mvvmproject.data.network.MyApi
import com.softlabit.mvvmproject.data.network.SafeApiRequest
import com.softlabit.mvvmproject.data.network.responses.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
): SafeApiRequest() {

    // User Login Async..
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(User(email, password)) }
    }

    suspend fun userSignup(
        email: String,
        password: String,
        name: String,
        phone: String
    ): AuthResponse {
        return apiRequest { api.userSignup(User(email, password, name, phone)) }
    }

    // Save User Data to Local Storage..
    suspend fun saveUser(user: com.softlabit.mvvmproject.data.db.entities.User) =
        db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}
