package com.example.lab2_koltinfundamentals

fun main(args: Array<String>) {
//    display("This is the value we want you to display")
//
//    val result = add(2, 4)
//    display(result.toString())

    val pet = Pet("Kuku" , "Hanin")
    println(pet)

}

data class Pet (var name : String , var owner : String , var petAge : Int = 10)

//val display = fun (value : String) { println(value) }
//
////fun display(value : String) { println(value) }
//
//fun add(a : Int , b: Int) : Int {
//    return a + b
//}
