package com.cmps312.viewmodeltutorial.repo

import com.cmps312.viewmodeltutorial.model.Student

object StudentRepo {
    private val students = mutableListOf(
        Student("John" , 19),
        Student("Ali" , 20),
        Student("Omar" , 21),
        Student("Sara" , 22),
        Student("Anfal" , 23),
    )
    fun getStudents() : List<Student> = students
}