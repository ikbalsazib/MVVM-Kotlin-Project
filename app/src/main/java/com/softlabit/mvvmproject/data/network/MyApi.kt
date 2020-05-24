package com.softlabit.mvvmproject.data.network

import com.softlabit.mvvmproject.data.model.User
import com.softlabit.mvvmproject.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
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
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {
            // Check Internet..

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient) // Check Internet Connection..
                .baseUrl("http://192.168.1.104:3001/api/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}