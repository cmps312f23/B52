package com.cmps312.litquest.entity

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Book(
    var title: String,
    var description: String,
    var image: String,
    var author: String,
    var isbn: String,
    var genreId : Int,
    var id : Int= Random.nextInt(1000)
)
