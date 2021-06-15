package com.example.demomvvm.screen.main

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demomvvm.data.model.Pokemon
import com.example.demomvvm.data.model.PokemonResponse
import com.example.demomvvm.data.repository.PokemonRepository
import com.example.demomvvm.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemons = MutableLiveData<Resource<PokemonResponse>>()
    val pokemons: LiveData<Resource<PokemonResponse>>
        get() = _pokemons

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    private var isLoadingMore = false

    init {
        getPokemons()
    }

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recycler: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recycler, dx, dy)
            (recycler.layoutManager as GridLayoutManager).run {
                if (dy > 0) {
                    if (!isLoadingMore && findLastCompletelyVisibleItemPosition() == itemCount - 1) {
                        isLoadingMore = true
                        loadMoreData()
                    }
                }
            }
        }
    }

    fun addToFavorite(pokemon: Pokemon, pos: Int) = viewModelScope.launch(Dispatchers.IO) {
        val check = repository.isFavoritePokemon(pokemon)
        if (check) {
            repository.removeFavoritePokemon(pokemon)
            pokemon.isFavorite = false
        } else {
            repository.addFavorites(pokemon)
            pokemon.isFavorite = true
        }
        pokemon.position = pos
        _pokemon.postValue(pokemon)
    }

    fun getPokemons() = viewModelScope.launch(Dispatchers.IO) {
        _pokemons.postValue(Resource.loading(null))
        try {
            val listFavorites = repository.getAllFavoritesPokemons()
            val response = repository.getPokemons(null, null)
            for (poke in response.results) {
                if (listFavorites.contains(poke)) {
                    poke.isFavorite = true
                }
            }
            _pokemons.postValue(Resource.success(response))
        } catch (e: Exception) {
            _pokemons.postValue(e.message?.let { Resource.error(null, it) })
        }
    }

    fun loadMoreData() {
        pokemons.value?.data?.next?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val offset = Uri.parse(it).getQueryParameter("offset")
                val limit = Uri.parse(it).getQueryParameter("limit")
                _pokemons.postValue(Resource.loading(null))
                try {
                    val listFavorites = repository.getAllFavoritesPokemons()
                    val response = repository.getPokemons(offset, limit)
                    for (poke in response.results) {
                        if (listFavorites.contains(poke)) {
                            poke.isFavorite = true
                        }
                    }
                    isLoadingMore = false
                    _pokemons.postValue(Resource.success(response))
                } catch (e: Exception) {
                    _pokemons.postValue(e.message?.let { Resource.error(null, it) })
                    isLoadingMore = false
                }
            }
        }
    }
}
