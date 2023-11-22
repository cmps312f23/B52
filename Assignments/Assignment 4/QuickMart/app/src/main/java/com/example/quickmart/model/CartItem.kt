package com.example.quickmart.model

import com.example.quickmart.repository.ProductRepository
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class CartItem(
    var productId: String,
    var quantity: Int,
    var id: String = UUID.randomUUID().toString(),
    var userId: String = UUID.randomUUID().toString()

) {
    fun calculateTotalPrice(): Double {
        return quantity * ProductRepository.getProduct(productId).price
    }
}