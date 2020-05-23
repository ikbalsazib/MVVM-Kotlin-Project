package com.softlabit.mvvmproject.data.network.responses

import com.softlabit.mvvmproject.data.db.entities.User

data class AuthResponse (
    val isSuccessful: String?,
    val message: String?,
    val user: User?,
    val token: String?,
    val expiredIn: Int?
)