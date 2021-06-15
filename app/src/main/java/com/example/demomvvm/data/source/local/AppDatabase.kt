package com.example.demomvvm.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demomvvm.data.model.Pokemon

@Database(entities = [Pokemon::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDAO
}
