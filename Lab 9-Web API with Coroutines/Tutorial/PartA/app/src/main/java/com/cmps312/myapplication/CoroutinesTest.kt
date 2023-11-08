package com.cmps312.myapplication

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    doWorld();
    println(Thread.currentThread().name)
}

suspend fun doWorld() = coroutineScope {
    val job= launch {
        delay(3000L)
        println("World First")
        println(Thread.currentThread().name)
    }

    println("Hello")
    job.join();
    print("Done")
}