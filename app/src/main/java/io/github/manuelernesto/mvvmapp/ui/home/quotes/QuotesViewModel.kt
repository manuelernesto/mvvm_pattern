package io.github.manuelernesto.mvvmapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import io.github.manuelernesto.mvvmapp.data.repository.QuoteRepository
import io.github.manuelernesto.mvvmapp.util.lazzyDeferred

class QuotesViewModel(
    quoteRepository: QuoteRepository
) : ViewModel() {
    val quotes by lazzyDeferred { quoteRepository.getQuotes() }
}
