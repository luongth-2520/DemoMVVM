package com.example.demomvvm.screen.adapter

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.demomvvm.BR
import com.example.demomvvm.R
import com.example.demomvvm.data.model.Pokemon
import com.example.demomvvm.databinding.ItemPokemonBinding
import com.example.demomvvm.utils.Constants
import com.example.demomvvm.utils.OnItemClickListener

class ItemPokemonViewModel(private val itemClickListener: OnItemClickListener<Pokemon>? = null) : BaseObservable() {

    @Bindable
    var pokemon: Pokemon? = null
    private var position = Constants.POSITION_DEFAULT

    fun setData(data: Pokemon?, binding: ItemPokemonBinding, pos: Int) {
        data?.let {
            pokemon = it
            position = pos
            if (it.isFavorite) {
                binding.imageFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                binding.imageFavorite.setImageResource(R.drawable.ic_favorite_border)
            }
            notifyPropertyChanged(BR.pokemon)
        }
    }

    fun onItemClicked(view: View) {
        itemClickListener?.let { listener ->
            pokemon?.let {
                listener.onItemViewClick(it, position)
            }
        }
    }
}
