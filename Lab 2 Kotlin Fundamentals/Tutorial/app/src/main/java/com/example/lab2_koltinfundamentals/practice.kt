package com.example.lab2_koltinfundamentals

fun main(args: Array<String>) {
////    print("hello")
//    System.out.println("Hello welcome to Kotlin")
//
////    val name = "Abdulahi"
////    var height = 1.78
//
//    val age : Int = 0
//
////    read a value from the user
//    println("Enter your name")
////    var name = readlnOrNull()
//
////    if(name != null)
////        println(name.length)
////    name = null
//    println("The length of your name is")
////    println("Hello  ${name?.length} ")
//    val salary = 20000
//    val result = if (salary > 15000)  {
//        val x = 10
//        "Nice"
//        10
//    } else if (salary > 1000) "good" else "God help you"
//    println(result)
//
//    var bio = """
//        I was born in Wonderland
//        In the year 1900
//        In a valley called Narnia
//    """.trimIndent()
//
//    print(bio)

//    val salary = 4000
//
//    val loanAmount = when(salary){
//        in 2000..3000 -> "Loan amount is 1000"
//        in 3000..20000 -> "Loan amount is 5000"
//        1000 -> "loan is 5000"
//        else -> "print no loan"
//    }
//    println(loanAmount)

    //mutable

    var arrayCars = arrayOf("Volvo", "BMW", "Ford", "Mazda")
    var listCars = mutableListOf("Volvo", "BMW","BMW", "BMW", "BMW", "Ford", "Mazda")
    var setCars = mutableSetOf("Volvo", "BMW", "BMW", "BMW", "Ford", "Mazda")
    var mapCars = mutableMapOf("V" to "Volvo", 3 to "BMW", "C" to "Ford", "M" to "Mazda")



//    mapCars["InventAKey"] = "value"

//    for ((k, v) in mapCars)
//        println("Key is $k value is $v")


    if("volvo" in arrayCars)
        println("yes it works")

    for (i in 1..100 step 4)
        print("$i ")

//    println("Array = ${arrayCars[1]}")
//
//    listCars.add(1, "Mercedes")
//
//    println("List = $listCars")
//    println("mapCars = ${mapCars[3]}")
//    println("Set = ${setCars.elementAt(2)}")
}