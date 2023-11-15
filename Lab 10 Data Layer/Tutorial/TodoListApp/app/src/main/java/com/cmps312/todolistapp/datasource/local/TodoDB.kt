package com.cmps312.todolistapp.datasource.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cmps312.todolistapp.entity.Project
import com.cmps312.todolistapp.entity.Todo

@Database(
    entities = [Todo::class, Project::class],
    version = 1
)
abstract class TodoDB : RoomDatabase() {
    abstract fun ProjectDao(): ProjectDao
    abstract fun TodoDao(): TodoDao

    companion object {
        private var db: TodoDB? = null
        fun getDatabase(context: Context): TodoDB {
            if (db == null) {
                db = Room.databaseBuilder(
                    context = context.applicationContext,
                    TodoDB::class.java,
                    "todo.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return db as TodoDB
        }
    }

}