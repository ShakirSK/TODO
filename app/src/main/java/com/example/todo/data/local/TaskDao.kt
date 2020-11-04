package com.example.todo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todo.data.local.TaskTableModel


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(loginTableModel: TaskTableModel)

    @Query("SELECT * FROM Task")
    suspend fun getTasks(): List<TaskTableModel>

    @Query("SELECT * FROM Task WHERE title = :taskTitle")
    suspend fun getTaskByTitle(taskTitle: String?): TaskTableModel


    @Query("UPDATE Task SET title =:title ,description=:description WHERE title =:titleName")
    suspend fun updateTask(titleName: String, title: String, description: String)
}