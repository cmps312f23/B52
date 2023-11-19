package com.cmps312.todolistapp.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ProjectWithTodos(
    @Embedded
    val project: Project,

    @Relation(parentColumn = "id", entityColumn = "pid")
    val todos: List<Todo>,
)