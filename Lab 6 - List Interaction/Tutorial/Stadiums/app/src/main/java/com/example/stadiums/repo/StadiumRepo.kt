package com.example.stadiums.repo

import android.content.Context
import com.example.stadiums.model.Stadium
import kotlinx.serialization.json.Json

object StadiumRepo {
    //    a variable that can strore the stadiums
    var stadiums = listOf<Stadium>()

    fun getStadiums(context: Context): List<Stadium> {
        if (stadiums.isEmpty()){
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
}