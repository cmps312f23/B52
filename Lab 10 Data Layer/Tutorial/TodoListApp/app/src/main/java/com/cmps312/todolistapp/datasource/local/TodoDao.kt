package com.cmps312.todolistapp.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.cmps312.todolistapp.entity.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo WHERE pid=:pid")
    fun observeTodos(pid: Int): Flow<List<Todo>>

    @Query("SELECT * FROM Todo WHERE id=:id")
    suspend fun getTodo(id: Int): Todo

    @Upsert
    suspend fun upsertTodo(todo: Todo): Long

    @Delete
    suspend fun deleteTodo(todo: Todo): Int

    @Query("SELECT * FROM Todo WHERE pid=:id")
    fun getTodoListByProject(id: Int): Flow<List<Todo>>

}