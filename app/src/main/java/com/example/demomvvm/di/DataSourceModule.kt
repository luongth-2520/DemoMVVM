package com.example.demomvvm.di

import androidx.room.Room
import com.example.demomvvm.data.source.PokemonDataSource
import com.example.demomvvm.data.source.local.AppDatabase
import com.example.demomvvm.data.source.local.PokemonDAO
import com.example.demomvvm.data.source.local.PokemonLocalImpl
import com.example.demomvvm.data.source.remote.PokemonRemoteImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<PokemonDataSource.Local> {
        PokemonLocalImpl(get())
    }

    single<PokemonDataSource.Remote> {
        PokemonRemoteImpl(get())
    }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "Pokedb").fallbackToDestructiveMigration().build()
    }

    fun providePokemonDao(database: AppDatabase): PokemonDAO {
        return database.pokemonDao()
    }

    single {
        providePokemonDao(get())
    }
}
