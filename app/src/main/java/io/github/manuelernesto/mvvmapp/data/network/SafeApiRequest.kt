package io.github.manuelernesto.mvvmapp.data.network

import io.github.manuelernesto.mvvmapp.util.ApiExeption
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()

            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                }
            }

            message.append("\nError Code: ${response.code()}")

            throw ApiExeption(message = message.toString())
        }
    }

}