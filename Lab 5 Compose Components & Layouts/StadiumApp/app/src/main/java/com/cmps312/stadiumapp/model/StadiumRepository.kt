package com.example.stadiumappprep.model

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object StadiumRepository {
    var stadiums = listOf<Stadium>()
    fun getStadiums(context: Context): List<Stadium> {
        if (stadiums.isEmpty()) {
            val stadiumJson = context.assets
                .open("stadiums.json")
                .bufferedReader()
                .use { it.readText() }
            println(stadiumJson)
            stadiums = Json.decodeFromString(stadiumJson)

        }
        return stadiums
    }
}



