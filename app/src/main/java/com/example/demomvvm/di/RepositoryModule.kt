package com.example.demomvvm.di

import com.example.demomvvm.data.repository.PokemonRepository
import com.example.demomvvm.data.repository.PokemonRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<PokemonRepository> {
        PokemonRepositoryImpl(get(), get())
    }
}
