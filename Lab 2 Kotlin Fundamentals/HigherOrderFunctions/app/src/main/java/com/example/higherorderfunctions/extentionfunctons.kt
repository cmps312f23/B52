package com.example.higherorderfunctions

fun main(args: Array<String>) {
    val myString:String = "Hello World"
    val num = -20

    num.isPositive()
//    println("The number of vowels are ${countVowels(myString)}")
    println("The number of vowels are ${myString.countVowels()}")

    println("This number $num is positive = ${num.isPositive()}")

}

fun Int.isPositive() = this >= 0

fun String.countVowels(): Int {

    var count = 0
    val vowels = "aeiou"

    for(i in this) {
        if (i in vowels)
            count++
    }

    return count
}