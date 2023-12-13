package com.cmps312.screenscores.entity

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable

data class Movie(
    val movieId: Long = Random.nextLong(1000000),
    val name: String = "",
    val actors: String = "",
    val releaseDate: String = "",
    val poster: String = ""
)
