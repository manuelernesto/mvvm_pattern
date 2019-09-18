package io.github.manuelernesto.mvvmapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService
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

        var result = false

        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }
}