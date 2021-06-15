package com.example.demomvvm.data.source.remote

import com.example.demomvvm.data.source.PokemonDataSource

class PokemonRemoteImpl(private val apiService: ApiService) : PokemonDataSource.Remote {
    override suspend fun getPokemons(offset: String?, limit: String?) =
        apiService.getListPokemon(offset, limit)
}
