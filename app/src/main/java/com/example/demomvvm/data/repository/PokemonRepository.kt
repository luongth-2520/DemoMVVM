package com.example.demomvvm.data.repository

import com.example.demomvvm.data.model.Pokemon
import com.example.demomvvm.data.model.PokemonResponse

interface PokemonRepository {
    suspend fun getPokemons(offset: String?, limit: String?): PokemonResponse
    suspend fun addFavorites(pokemon: Pokemon)
    suspend fun isFavoritePokemon(pokemon: Pokemon): Boolean
    suspend fun getAllFavoritesPokemons(): MutableList<Pokemon>
    suspend fun removeFavoritePokemon(pokemon: Pokemon)
}
