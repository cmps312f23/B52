package com.example.quickmart.data.repository

import android.content.Context
import com.example.quickmart.data.model.Product
import kotlinx.serialization.json.Json

object FavouriteRepository {
    private var favouriteItems = mutableListOf<Product>()
    fun getFavourites() = favouriteItems
    fun addToFavourites(product: Product) {
        favouriteItems.add(product)
    }

    //    This is just for testing purpose only, to show you the fav page
    fun testingFavourites(context: Context): List<Product> = Json.decodeFromString<List<Product>>(
        context.assets.open("products.json").bufferedReader().use { it.readText() })
        .subList(1, 4)
        .toMutableList()
}

fun main(args: Array<String>) {
    val fav = listOf(1, 2, 3, 4, 5, 5)

    print(fav.subList(1, 4))
}