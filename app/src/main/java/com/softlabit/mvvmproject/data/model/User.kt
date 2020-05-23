package com.softlabit.mvvmproject.data.model

data class User(
    var email: String,
    var password: String,
    var name: String? = null,
    var phone: String? = null
)