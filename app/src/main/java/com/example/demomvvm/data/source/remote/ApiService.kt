package com.example.demomvvm.data.source.remote

import com.example.demomvvm.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getListPokemon(@Query("offset") offset: String?, @Query("limit") limit: String?): PokemonResponse
}
