package com.example.higherorderfunctions

fun main(args: Array<String>) {
    val numbers = listOf(1, -2, 3, -4, 5, -6)

    val squared = numbers.map { it *it}
    val findPositiveNumbers =   numbers.find { it > 0 }
    val findLastPostiveNumber = numbers.findLast { it > 0 }

    val allNegative = numbers.filter { it < 0 }

    val sumAll = numbers.reduce { sum, nextElement -> sum + nextElement }
    val multiply = numbers.reduce { sum, nextElement -> sum * nextElement }

    val sumAll2 = numbers.fold(10) { sum, nextElement -> sum + nextElement }

//    sum = 1  and nextElement = -2
    // sum = -1 and nextElement = 3
    // sum = 2 and nextElement = -4
    // sum = -2 and nextElement = 5
    // sum = 3 and nextElement = -6
    // sum = -3


//    display all the number in this format -i
    numbers.forEach {print(" >> $it")}
    println("Map to Squared numbers are $squared")
    println("Find First Positive number is $findPositiveNumbers")
    println("Find Last positive number is $findLastPostiveNumber")
    println("Filter All negative numbers are $allNegative")
//    println("All negative numbers are $allNegative")

}