package com.softlabit.mvvmproject.data.network

import com.google.gson.JsonIOException
import com.softlabit.mvvmproject.util.ApiException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            val error = response.errorBody()?.string()

            val message = StringBuilder()

            error?.let {
                try {
                    message.append(JSONObject(it).get("message"))
                } catch (e: JsonIOException) {}
                message.append("\n")
            }
            message.append("Error code: ${response.code()}")

            // Throw API Exception...
            throw ApiException(message.toString())
        }
    }

}
