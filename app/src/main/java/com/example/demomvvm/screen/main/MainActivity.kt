package com.example.demomvvm.screen.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demomvvm.R
import com.example.demomvvm.data.model.Pokemon
import com.example.demomvvm.databinding.ActivityMainBinding
import com.example.demomvvm.screen.adapter.PokemonAdapter
import com.example.demomvvm.utils.OnItemClickListener
import com.example.demomvvm.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnItemClickListener<Pokemon> {

    private val pokemonAdapter by lazy { PokemonAdapter() }
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        registerLiveData()
    }

    private fun bindView() {
        binding.run {
            swipeRefreshMain.run {
                setOnRefreshListener {
                    pokemonAdapter.clear()
                    isRefreshing = false
                    mainViewModel.getPokemons()
                }
            }
            recyclerPokemon.addOnScrollListener(mainViewModel.scrollListener)
        }
        pokemonAdapter.registerItemClickListener(this)
    }

    override fun onStart() {
        bindView()
        super.onStart()
    }

    override fun onStop() {
        pokemonAdapter.unRegisterItemClickListener()
        super.onStop()
    }

    private fun setUpView() {
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                viewModel = mainViewModel
                adapter = pokemonAdapter
            }
    }

    private fun registerLiveData() {
        mainViewModel.pokemons.observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        pokemonAdapter.updateData(it.data?.results)
                        binding.progressBarLoading.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        binding.progressBarLoading.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressBarLoading.visibility = View.VISIBLE
                    }
                }
            }
        })

        mainViewModel.pokemon.observe(this, {
            pokemonAdapter.setFavorites(it.position, it)
        })
    }

    override fun onItemViewClick(item: Pokemon, position: Int) {
        mainViewModel.addToFavorite(item, position)
    }
}
