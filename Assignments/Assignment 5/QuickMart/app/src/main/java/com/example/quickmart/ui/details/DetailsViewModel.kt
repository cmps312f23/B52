package com.example.quickmart.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.quickmart.model.CartItem
import com.example.quickmart.repository.CartRepository
import com.example.quickmart.repository.ProductRepository

class DetailsViewModel : ViewModel() {

    var product by mutableStateOf(ProductRepository.currentProduct)
        private set

    var quantity by mutableIntStateOf(1)
        private set
    var total by mutableDoubleStateOf(product.price * quantity)
        private set
    var isFavourite by mutableStateOf(false)
        private set

    /*
     var isFavourite by mutableStateOf(FavouriteRepository.favouriteItems.contains(product))
     */
    fun updateQuantity(quantity: Int, price: Double) {
        this.quantity += quantity
        total = price * this.quantity
    }


    fun addToCart(price: Double, productId: String) {
        CartRepository.upsertCartItem(CartItem(productId, quantity))
        quantity = 1
        total = price * quantity
    }
}