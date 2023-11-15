package com.cmps312.todolistapp.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.cmps312.todolistapp.entity.Project
import com.cmps312.todolistapp.entity.ProjectWithTodos
import kotlinx.coroutines.flow.Flow
