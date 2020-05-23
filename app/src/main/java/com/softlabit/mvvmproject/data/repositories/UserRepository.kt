package com.softlabit.mvvmproject.data.repositories

import com.softlabit.mvvmproject.data.model.User
import com.softlabit.mvvmproject.data.network.MyApi
import com.softlabit.mvvmproject.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository {
    suspend fun userLogin(email: String, password: String): Response<AuthResponse> {
        return MyApi().userLogin(User(email, password))
    }
}




/*
val loginResponse = MutableLiveData<String>()

MyApi().userLogin(User(email, password))
.enqueue(object: Callback<ResponseBody> {
    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        loginResponse.value = t.message
    }

    override fun onResponse(
        call: Call<ResponseBody>,
        response: Response<ResponseBody>
    ) {
        if (response.isSuccessful) {
            loginResponse.value = response.body()?.string()
        } else {
            loginResponse.value = response.errorBody()?.string()
        }
    }

})

return loginResponse

*/