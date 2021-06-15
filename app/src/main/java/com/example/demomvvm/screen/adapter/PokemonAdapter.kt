package com.example.demomvvm.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demomvvm.R
import com.example.demomvvm.data.model.Pokemon
import com.example.demomvvm.databinding.ItemPokemonBinding
import com.example.demomvvm.utils.OnItemClickListener

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.ItemViewHolder>() {

    private val pokemons = mutableListOf<Pokemon>()
    private var itemClickListener: OnItemClickListener<Pokemon>? = null

    fun updateData(newData: MutableList<Pokemon>?) {
        newData?.let {
            val oldListSize = pokemons.size
            pokemons.addAll(it)
            if (oldListSize < 1) {
                notifyItemRangeInserted(0, it.size)
            } else {
                notifyItemRangeInserted(oldListSize, oldListSize + it.size)
            }
        }
    }

    fun clear() {
        val oldListSize = pokemons.size
        pokemons.clear()
        notifyItemRangeRemoved(0, oldListSize)
    }

    fun setFavorites(position: Int, pokemon: Pokemon) {
        pokemons[position] = pokemon
        notifyItemChanged(position)
    }

    fun registerItemClickListener(onItemClickListener: OnItemClickListener<Pokemon>) {
        itemClickListener = onItemClickListener
    }

    fun unRegisterItemClickListener() {
        itemClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemPokemonBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_pokemon,
            parent,
            false
        )
        return ItemViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount() = pokemons.size

    class ItemViewHolder(
        private val binding: ItemPokemonBinding,
        private val itemClickListener: OnItemClickListener<Pokemon>?,
        private val itemViewModel: ItemPokemonViewModel = ItemPokemonViewModel(itemClickListener)
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewModel = itemViewModel
        }

        fun bind(pokemon: Pokemon?) {
            itemViewModel.setData(pokemon, binding, adapterPosition)
            binding.executePendingBindings()
        }
    }
}
