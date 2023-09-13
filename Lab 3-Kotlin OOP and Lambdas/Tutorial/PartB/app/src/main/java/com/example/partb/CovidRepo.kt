package com.example.partb

import com.example.partb.model.CovidStat
import kotlinx.serialization.json.Json
import java.io.File

object CovidRepo {
    var covidData = listOf<CovidStat>()

    init {
//        read the file content using java file system
        val fileContent = File("data/covid-data.json").readText()

//        convert the text format into a list of covid stat object that we can work with
        covidData = Json { ignoreUnknownKeys = true }.decodeFromString(fileContent)
    }

    val populationOfCountry = { country: String -> covidData.find { it.country == country }?.population }

}

fun main(args: Array<String>) {
    println(CovidRepo.covidData)
    val populationOfSpain =
        println("the population of spain is $populationOfSpain")
}