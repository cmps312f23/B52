package com.cmps312.screenscores.entity

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val ratingId: Long,
    val score: Double,
    val comment: String,
    val movieId: Long
)

