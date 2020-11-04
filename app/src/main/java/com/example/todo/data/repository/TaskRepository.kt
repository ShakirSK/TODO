package com.example.todo.data.repository

import com.example.todo.data.local.TaskTableModel
import com.example.todo.data.local.TodoDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(private val todoDatabase : TodoDatabase,
                     private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO){


     suspend fun saveTask(task: TaskTableModel) = withContext(ioDispatcher) {
         todoDatabase.taskDao().insertTask(task)
    }

     suspend fun getTasks(): List<TaskTableModel> = withContext(ioDispatcher) {

         todoDatabase.taskDao().getTasks()
     }

    suspend fun getTasksByTitle(title: String?): TaskTableModel = withContext(ioDispatcher) {

        todoDatabase.taskDao().getTaskByTitle(title)
    }

    suspend fun updateTask(titleName: String,title: String,description: String)= withContext(ioDispatcher) {
        todoDatabase.taskDao().updateTask(titleName,title,description)
    }

}