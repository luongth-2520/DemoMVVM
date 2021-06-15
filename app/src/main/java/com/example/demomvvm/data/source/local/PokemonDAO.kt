package com.example.demomvvm.data.source.local

import androidx.room.*
import com.example.demomvvm.data.model.Pokemon

@Dao
interface PokemonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: Pokemon)

    @Delete
    suspend fun delete(pokemon: Pokemon)

    @Query("SELECT * FROM Favorites_Pokemon")
    fun getAllPokemons(): MutableList<Pokemon>

    @Query("SELECT EXISTS(SELECT * FROM Favorites_Pokemon WHERE name = :pokemonName)")
    suspend fun isFavoritesPokemon(pokemonName: String): Boolean
}
