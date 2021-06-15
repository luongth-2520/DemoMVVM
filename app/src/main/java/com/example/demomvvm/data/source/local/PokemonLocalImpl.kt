package com.example.demomvvm.data.source.local

import com.example.demomvvm.data.model.Pokemon
import com.example.demomvvm.data.source.PokemonDataSource

class PokemonLocalImpl(private val pokemonDAO: PokemonDAO) : PokemonDataSource.Local {

    override suspend fun addFavoritePokemon(pokemon: Pokemon) = pokemonDAO.insert(pokemon)

    override suspend fun isFavoritePokemon(pokemon: Pokemon) =
        pokemonDAO.isFavoritesPokemon(pokemon.name)

    override suspend fun getAllPokemons(): MutableList<Pokemon> = pokemonDAO.getAllPokemons()

    override suspend fun removeFavoritePokemon(pokemon: Pokemon) = pokemonDAO.delete(pokemon)
}
