package com.softlabit.mvvmproject.data.network

import com.softlabit.mvvmproject.data.model.User
import com.softlabit.mvvmproject.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApi {

    @POST("login")
    // Responsible with Coroutine..
    suspend fun userLogin(
        @Body user: User
    ): Response<AuthResponse>

    // Call the Instance of MyAPI like MyAPI()...
    companion object{
        operator fun invoke() =
            Retrofit.Builder()
                .baseUrl("http://192.168.1.104:3001/api/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
    }
}