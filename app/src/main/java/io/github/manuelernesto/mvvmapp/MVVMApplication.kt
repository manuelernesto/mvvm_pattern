package io.github.manuelernesto.mvvmapp

import android.app.Application
import io.github.manuelernesto.mvvmapp.data.db.AppDatabase
import io.github.manuelernesto.mvvmapp.data.network.MyAPI
import io.github.manuelernesto.mvvmapp.data.network.NetworkConnectionInterceptor
import io.github.manuelernesto.mvvmapp.data.preferences.PreferenceProvider
import io.github.manuelernesto.mvvmapp.data.repository.QuoteRepository
import io.github.manuelernesto.mvvmapp.data.repository.UserRepository
import io.github.manuelernesto.mvvmapp.ui.auth.AuthViewModelFactory
import io.github.manuelernesto.mvvmapp.ui.home.profile.ProfileViewModelFactory
import io.github.manuelernesto.mvvmapp.ui.home.quotes.QuoteViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {

        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyAPI(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }

        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuoteRepository(instance(), instance(), instance()) }

        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuoteViewModelFactory(instance()) }

    }
}