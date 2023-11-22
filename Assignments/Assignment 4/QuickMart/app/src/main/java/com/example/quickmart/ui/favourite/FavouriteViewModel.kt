package com.example.quickmart.ui.favourite

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.quickmart.model.Favourite
import com.example.quickmart.model.Product
import com.example.quickmart.repository.CartRepository
import com.example.quickmart.repository.FavouriteRepository
import com.example.quickmart.repository.ProductRepository

class FavouriteViewModel : ViewModel() {
    private var _favourites = FavouriteRepository.getFavouriteItems().toMutableStateList()

    val favourites: List<Favourite> = _favourites

    fun onRemoveProductFromFavourite(product: Product) {
        FavouriteRepository.removeFromFavourites(product.id)
        refresh()
    }

    fun addAllFavToCart() {
        val productIds = FavouriteRepository.getFavouriteItems().map { it.productId }
        val products = ProductRepository.getMatchingProducts(productIds)
        CartRepository.addCartItems(products)
        FavouriteRepository.clearItems()
        refresh()
    }

    fun addToFavourite(product: Product) {
        FavouriteRepository.addToFavourites(Favourite(product.id))
        refresh()
    }

    fun isInFavourite(product: Product): Boolean = FavouriteRepository.isInFavourite(product.id)

    fun getMatchingProducts(): List<Product> =
        ProductRepository.getMatchingProducts(favourites.map { it.productId })

    private fun refresh() {
        _favourites.clear()
        _favourites.addAll(FavouriteRepository.getFavouriteItems())
    }
}