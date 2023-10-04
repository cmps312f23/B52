package com.example.stadiums.repo

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.stadiums.model.Stadium
import kotlinx.serialization.json.Json

object SortOptions {
    const val NAME = "NAME"
    const val CITY = "CITY"
    const val SEATING_CAPACITY = "SEATING_CAPACITY"
    const val NONE = "NONE"
}
object StadiumRepo {
    //    a variable that can strore the stadiums
    var stadiums = listOf<Stadium>()

    fun getStadiums(context: Context): List<Stadium> {
        if (stadiums.isEmpty()) {
            //        text content of the file can be read as
            val stadiumJsonText = context
                .assets
                .open("stadiums.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
//        convert the json text to a list of stadiums
            stadiums = Json { ignoreUnknownKeys = true }
                .decodeFromString(stadiumJsonText)
        }

        return stadiums
    }

    fun filterStadiums(query: String) = stadiums.filter {
        it.name.contains(query , ignoreCase = true) or it.city.contains(query , ignoreCase = true)
    }

    fun sortStadiums(sortOption : String) = when(sortOption) {
        SortOptions.NAME -> stadiums.sortedBy { it.name }
        SortOptions.CITY -> stadiums.sortedBy { it.city }
        SortOptions.SEATING_CAPACITY -> stadiums.sortedBy { it.seatingCapacity }
        else -> stadiums
    }
}

