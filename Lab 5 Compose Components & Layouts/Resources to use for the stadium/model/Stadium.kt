package com.example.stadiumappprep.model

import kotlinx.serialization.Serializable

@Serializable
data class Stadium(
    var name:String,
    var city:String,
    var status:String,
    var imageName:String
)
