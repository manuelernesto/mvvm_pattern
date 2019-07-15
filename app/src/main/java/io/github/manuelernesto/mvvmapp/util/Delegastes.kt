package io.github.manuelernesto.mvvmapp.util

import kotlinx.coroutines.*

fun <T> lazzyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}