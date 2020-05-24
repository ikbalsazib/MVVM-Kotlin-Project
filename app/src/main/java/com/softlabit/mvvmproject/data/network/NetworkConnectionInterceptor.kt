package com.softlabit.mvvmproject.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.softlabit.mvvmproject.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetConnected())
            throw NoInternetException("No Internet Connection :)")
        return chain.proceed(chain.request())
    }

//    private fun isInternetAvailable() : Boolean{
//        val connectivityManager =
//            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        connectivityManager.activeNetworkInfo.also {
//            return it != null && it.isConnected
//        }
//    }

    private fun isInternetConnected():Boolean{

        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                else -> false
            }
        }
        else {
            @Suppress("DEPRECATION")
            return connectivityManager.activeNetworkInfo != null &&
                    connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
        }
    }

}