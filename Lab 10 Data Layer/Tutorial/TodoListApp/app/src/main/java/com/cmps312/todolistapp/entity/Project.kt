package com.cmps312.todolistapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Project(
    val name: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
