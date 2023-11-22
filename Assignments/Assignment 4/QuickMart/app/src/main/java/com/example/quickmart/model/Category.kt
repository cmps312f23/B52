package com.example.quickmart.model

import kotlinx.serialization.Serializable

@Serializable
class Category(
    var category: String = "",
    var id: Int = 0
)

