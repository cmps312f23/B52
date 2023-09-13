package com.example.higherorderfunctions

import java.util.function.BiPredicate

fun main() {
    val a = 10
    val b = 20
    val sum = add(a, b);
    val sum2 = add(10, 20);

    println("Sum is $sum")

    specialFunction { a: Int -> a % 2 == 0 }
    specialFunction { a: Int -> a % 2 != 0 }
    specialFunction { a: Int -> a < 0 }


    specialFunction2 (10)  { a: Int -> a < 0 }

    specialFunction(isNeg)
    specialFunction(isEven)
    specialFunction(isOdd)


}


fun specialFunction2(a : Int , predicate: (Int) -> Boolean) {
    for (i in 1..100) {
        if (predicate(i))
            print("$i ")
    }
    println()
}

fun specialFunction(predicate: (Int) -> Boolean) {
    for (i in 1..100) {
        if (predicate(i))
            print("$i ")
    }
    println()
}

//val isEven = fun(a: Int): Boolean { return a % 2 == 0 }
val isEven = { a: Int -> a % 2 == 0 }

//val isOdd = fun(a: Int): Boolean { return a % 2 != 0 }
val isOdd = { a: Int -> a % 2 != 0 }

//val isNeg = fun(a: Int): Boolean { return a < 0 }
val isNeg = { a: Int -> a < 0 }

fun add(a: Int, b: Int): Int {
    return a + b
}