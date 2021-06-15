package com.example.demomvvm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorites_Pokemon")
data class Pokemon(
    @PrimaryKey
    val name: String,
    val url: String,
) {
    @Transient
    var isFavorite: Boolean = false

    @Transient
    var position: Int = 0

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
    }
}
