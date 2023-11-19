package com.cmps312.todolistapp.datasource.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cmps312.todolistapp.entity.Project
import com.cmps312.todolistapp.entity.Todo

@Database(entities = [Todo::class, Project::class], version = 1, exportSchema = false)
abstract class TodoDB: RoomDatabase() {
    abstract fun todoDao(): TodoDao
    abstract fun projectDao(): ProjectDao

    companion object {
        private var database: TodoDB? = null

        fun getDatabase(context: Context): TodoDB {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDB::class.java,
                    "todo_db"
                ).fallbackToDestructiveMigration().build()
            }
            return database as TodoDB
        }
    }

}