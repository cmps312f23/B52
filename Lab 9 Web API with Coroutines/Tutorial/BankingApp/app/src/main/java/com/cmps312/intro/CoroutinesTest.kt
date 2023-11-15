package com.cmps312.intro

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking{
    for(i in 10 .. 50000) {
        launch {
            delay((1000..5000L).random())
            print("$i ")
        }
    }
}

//suspend fun coroutineTest() = coroutineScope {
//    var x = 0
//    val job =  launch {
//        delay(1000L)
//        println("Hello 1")
//        x=10
//    }
//
//    launch {
//        println("Hello 2")
//    }
//    job.join()
//    println(100/x)
//    println("World")
//}
