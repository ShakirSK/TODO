package com.example.todo.data.local

import android.content.Context
import androidx.room.*

@Database(entities = [TaskTableModel::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    companion object {

        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabaseClient(context: Context) : TodoDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, TodoDatabase::class.java, "TODO_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}