package com.softlabit.mvvmproject

import android.app.Application
import com.softlabit.mvvmproject.data.db.AppDatabase
import com.softlabit.mvvmproject.data.network.MyApi
import com.softlabit.mvvmproject.data.network.NetworkConnectionInterceptor
import com.softlabit.mvvmproject.data.repositories.UserRepository
import com.softlabit.mvvmproject.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        //..
        bind() from provider { AuthViewModelFactory(instance()) }
    }
}