package com.example.quickmart.repository

import android.util.Log
import com.example.quickmart.model.CartItem
import com.example.quickmart.model.Product

object CartRepository {
    private var cartItems = mutableListOf<CartItem>();

    fun clearItems() = cartItems.clear()

    fun getCartItems(): List<CartItem> = cartItems

    fun removeCartItem(cartItem: CartItem) {
        cartItems.removeIf { it.productId == cartItem.productId }
    }

    fun upsertCartItem(cartItem: CartItem) {

        val item = cartItems.find { it.productId == cartItem.productId }

        //this means item is not in the cart
        if (item == null) {
            cartItems.add(cartItem)
            Log.d("TAG", "item is null: ")
        } else {
            //we have the item in the cart
            cartItems[cartItems.indexOf(item)].quantity += cartItem.quantity
            Log.d("TAG", "updating quantity: " + cartItems[cartItems.indexOf(item)].quantity)

            //after adding the quantity, if the quantity is 0, we remove the item from the cart
            if (item.quantity == 0)
                cartItems.remove(item)
        }

    }


    fun getCartTotal(): Double {
        return cartItems.fold(0.0) { acc, it -> acc + it.calculateTotalPrice() }
    }
//    multiple cart items
    fun addCartItems(products: List<Product>) = products.map { CartItem(it.id, 1) }
        .forEach { upsertCartItem(it) }


}