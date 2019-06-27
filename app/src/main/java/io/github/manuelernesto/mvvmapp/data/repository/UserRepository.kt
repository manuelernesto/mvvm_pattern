package io.github.manuelernesto.mvvmapp.data.repository

import io.github.manuelernesto.mvvmapp.data.network.MyAPI
import io.github.manuelernesto.mvvmapp.data.network.SafeApiRequest
import io.github.manuelernesto.mvvmapp.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest {
            MyAPI.invoke().userLogin(email, password)
        }
    }
}