package io.github.manuelernesto.mvvmapp.data.repository

import io.github.manuelernesto.mvvmapp.data.network.MyAPI
import io.github.manuelernesto.mvvmapp.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(email: String, password: String): Response<AuthResponse> {
        return MyAPI.invoke().userLogin(email, password)
    }
}