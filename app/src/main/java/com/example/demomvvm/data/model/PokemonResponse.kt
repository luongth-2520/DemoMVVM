package com.example.demomvvm.data.model

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: MutableList<Pokemon>
)
