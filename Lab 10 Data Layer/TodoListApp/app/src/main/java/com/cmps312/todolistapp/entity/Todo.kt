package com.cmps312.todolistapp.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//One to Many [One project can have one or more todos]

data class Todo(
    var title: String? = null,
    var priority: String? = null,
    var pid: Int,

    val id: Int = 0,
)
