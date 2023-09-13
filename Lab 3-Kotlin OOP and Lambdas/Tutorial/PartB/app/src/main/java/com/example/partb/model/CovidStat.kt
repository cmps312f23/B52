package com.example.partb.model

import kotlinx.serialization.Serializable


@Serializable
data class CovidStat(
    var id: Int,
    var country: String,
    var continent: String,
    var region: String,
    var totalCases: Int?,
    var totalDeaths: Int?,
    var newDeaths: Int?,
    var totalRecovered: Int?,
    var newRecovered: Int?,
    var activeCases: Int?,
    var population: Int?
)