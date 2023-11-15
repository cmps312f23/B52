package com.cmps312.todolistapp.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ProjectWithTodos(

    val project: Project,

    val todos: List<Todo>
)