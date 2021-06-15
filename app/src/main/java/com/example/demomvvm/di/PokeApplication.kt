package com.example.demomvvm.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = listOf(
            appModule,
            repositoryModule,
            dataSourceModule,
            networkModule,
            viewModelModule
        )
        startKoin {
            androidLogger()
            androidContext(this@PokeApplication)
            modules(modules)
        }
    }
}
