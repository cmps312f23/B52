package com.example.higherorderfunctions

fun main(args: Array<String>) {
    println("The number of vowels are ${countVowels()}")
}


fun countVowels(): Int {
    val string = "Hello World"
    var count = 0
    val vowels = "aeiou"

    for(i in string) {
        if (i in vowels)
            count++
    }

    return count
}