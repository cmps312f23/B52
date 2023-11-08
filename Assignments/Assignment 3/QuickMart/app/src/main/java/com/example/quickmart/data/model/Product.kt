package com.example.quickmart.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    var id: String,
    var title: String,
    var category: String,
    var description: String,
    var price: Double,
    var rating: Int,
    var imageName: String
) {}