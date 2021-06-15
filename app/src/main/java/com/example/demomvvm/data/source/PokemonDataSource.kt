package com.example.demomvvm.data.source

import com.example.demomvvm.data.model.Pokemon
import com.example.demomvvm.data.model.PokemonResponse

interface PokemonDataSource {

    interface Local {
        suspend fun addFavoritePokemon(pokemon: Pokemon)
        suspend fun isFavoritePokemon(pokemon: Pokemon): Boolean
        suspend fun getAllPokemons(): MutableList<Pokemon>
        suspend fun removeFavoritePokemon(pokemon: Pokemon)
    }

    interface Remote {
        suspend fun getPokemons(offset: String?, limit: String?): PokemonResponse
    }
}
