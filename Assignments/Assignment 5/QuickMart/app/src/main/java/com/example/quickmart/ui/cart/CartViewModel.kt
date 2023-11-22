package com.example.quickmart.ui.cart

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.quickmart.model.CartItem
import com.example.quickmart.model.Product
import com.example.quickmart.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartViewModel : ViewModel() {
    private val _cartItems = CartRepository.getCartItems().toMutableStateList()
    val cartItems: List<CartItem> = _cartItems

    private val _total = MutableStateFlow(CartRepository.getCartTotal())
    val total = _total.asStateFlow()

    fun addOrUpdate(productId: String, quantity: Int) {
        CartRepository.upsertCartItem(CartItem(productId, quantity))
        refresh()
    }

    fun clearCart() {
        CartRepository.clearItems()
        refresh()
    }

    fun getQuantity(product: Product) =
        _cartItems.firstOrNull { it.productId == product.id }?.quantity

    fun removeCartItem(item: CartItem) {
        CartRepository.removeCartItem(item)
        refresh()
    }

    private fun refresh() {
        _cartItems.clear()
        _cartItems.addAll(CartRepository.getCartItems())
        _total.value = CartRepository.getCartTotal()
    }

}