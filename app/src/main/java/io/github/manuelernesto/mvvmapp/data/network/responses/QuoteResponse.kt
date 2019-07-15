package io.github.manuelernesto.mvvmapp.data.network.responses

import io.github.manuelernesto.mvvmapp.data.db.entities.Quote

data class QuoteResponse(
    val isSuccessful: Boolean?,
    val quotes: List<Quote>?
)