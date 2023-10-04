package com.example.stadiums.model

import kotlinx.serialization.Serializable

@Serializable
data class Stadium(
    val name: String,
    val city: String,
    val status: String,
    val imageName: String,
    val seatingCapacity: Int
)