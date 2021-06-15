package com.example.demomvvm.di

import com.example.demomvvm.utils.Constants
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single { named(Constants.BASE_URL) }
}
