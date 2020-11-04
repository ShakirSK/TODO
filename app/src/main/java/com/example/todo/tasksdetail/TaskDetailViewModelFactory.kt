package com.example.todo.tasksdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.data.repository.TaskRepository

@Suppress("UNCHECKED_CAST")
class TaskDetailViewModelFactory (
    private val repository: TaskRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskDetailViewModel(repository) as T
    }


}
