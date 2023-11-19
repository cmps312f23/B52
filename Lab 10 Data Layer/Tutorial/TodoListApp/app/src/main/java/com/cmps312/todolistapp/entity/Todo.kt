package com.cmps312.todolistapp.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//One to Many [One project can have one or more todos]

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Project::class,
            parentColumns = ["id"],
            childColumns = ["pid"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class Todo(
    var title: String? = null,
    var priority: String? = null,
    var date: String? = null,
    var time: String? = null,
    var pid: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
