package com.example.quickmart.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    var id: String,
    var title: String,
    var description: String,
    var price: Double,
    var rating: Int,
    var imageName: String,
    var categoryId: Int
)