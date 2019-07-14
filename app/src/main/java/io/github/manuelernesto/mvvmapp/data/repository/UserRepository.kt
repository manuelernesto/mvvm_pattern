package io.github.manuelernesto.mvvmapp.data.repository

import io.github.manuelernesto.mvvmapp.data.db.AppDatabase
import io.github.manuelernesto.mvvmapp.data.db.entities.User
import io.github.manuelernesto.mvvmapp.data.network.MyAPI
import io.github.manuelernesto.mvvmapp.data.network.SafeApiRequest
import io.github.manuelernesto.mvvmapp.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api: MyAPI,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest {
            api.userLogin(email, password)
        }
    }

    suspend fun userSignup(name: String, email: String, password: String): AuthResponse {
        return apiRequest {
            api.userSignup(name, email, password)
        }
    }

    suspend fun saveUser(user: User) = db.getUserDAO().upsert(user)
    fun getUser() = db.getUserDAO().getUser()
}