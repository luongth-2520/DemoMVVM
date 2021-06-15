package com.example.demomvvm.di

import com.example.demomvvm.data.source.remote.ApiService
import com.example.demomvvm.data.source.remote.ServiceGenerator
import com.example.demomvvm.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module

val networkModule = module {
    single<Gson> {
        GsonBuilder().create()
    }
    single {
        ServiceGenerator.generate(
            baseUrl = Constants.BASE_URL,
            serviceClass = ApiService::class.java,
            gson = get()
        )
    }
}
