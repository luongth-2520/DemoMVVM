package com.example.demomvvm.data.repository

import com.example.demomvvm.data.model.Pokemon
import com.example.demomvvm.data.source.PokemonDataSource

class PokemonRepositoryImpl(
    private val remote: PokemonDataSource.Remote,
    private val local: PokemonDataSource.Local
) : PokemonRepository {

    override suspend fun getPokemons(offset: String?, limit: String?) = remote.getPokemons(offset, limit)

    override suspend fun addFavorites(pokemon: Pokemon) = local.addFavoritePokemon(pokemon)

    override suspend fun isFavoritePokemon(pokemon: Pokemon): Boolean =
        local.isFavoritePokemon(pokemon)

    override suspend fun getAllFavoritesPokemons() = local.getAllPokemons()

    override suspend fun removeFavoritePokemon(pokemon: Pokemon) =
        local.removeFavoritePokemon(pokemon)
}
