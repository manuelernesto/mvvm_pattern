package io.github.manuelernesto.mvvmapp.util

import android.accounts.NetworkErrorException
import java.io.IOException

class ApiExeption(message: String) : IOException(message)
class NoInternetExeption(message: String) : IOException(message)