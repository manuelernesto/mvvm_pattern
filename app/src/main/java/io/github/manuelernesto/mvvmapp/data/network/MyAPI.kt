package io.github.manuelernesto.mvvmapp.data.network

import io.github.manuelernesto.mvvmapp.data.network.responses.AuthResponse
import io.github.manuelernesto.mvvmapp.data.network.responses.QuoteResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyAPI {

    @FormUrlEncoded
    @POST(value = "login")
    suspend fun userLogin(
        @Field(value = "email") email: String,
        @Field(value = "password") password: String
    ): Response<AuthResponse>

    @FormUrlEncoded
    @POST(value = "signup")
    suspend fun userSignup(
        @Field(value = "name") name: String,
        @Field(value = "email") email: String,
        @Field(value = "password") password: String
    ): Response<AuthResponse>


    @GET("quotes")
    suspend fun getQuotes(): Response<QuoteResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyAPI {

            val okkHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit
                .Builder()
                .client(okkHttpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyAPI::class.java)
        }
    }
}