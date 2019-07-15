package io.github.manuelernesto.mvvmapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.manuelernesto.mvvmapp.data.db.AppDatabase
import io.github.manuelernesto.mvvmapp.data.db.entities.Quote
import io.github.manuelernesto.mvvmapp.data.network.MyAPI
import io.github.manuelernesto.mvvmapp.data.network.SafeApiRequest
import io.github.manuelernesto.mvvmapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository(
    private val api: MyAPI,
    private val db: AppDatabase
) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDAO().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {
        if (isFetchRequired()) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchRequired() = true


    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            db.getQuoteDAO().saveAllQuotes(quotes)
        }
    }
}