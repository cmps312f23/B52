package com.example.quickmart.repository

import com.example.quickmart.model.Favourite

object FavouriteRepository {
    private var favourites = mutableListOf<Favourite>();
    fun clearItems() = favourites.clear()
    fun getFavouriteItems(): List<Favourite> = favourites
    fun addToFavourites(favItem: Favourite) {
        val item = favourites.find { it.productId == favItem.productId }
        if (item == null) favourites.add(favItem)
    }

    fun removeFromFavourites(productId: String) {
        favourites = favourites.filter { it.productId != productId }.toMutableList()
    }

    fun isInFavourite(id: String): Boolean = favourites.find { it.productId == id } != null
}