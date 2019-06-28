package io.github.manuelernesto.mvvmapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import io.github.manuelernesto.mvvmapp.util.NoInternetExeption
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvailable())
            throw  NoInternetExeption("Make sure you have an active internet connection")

        return chain.proceed(chain.request())

    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}