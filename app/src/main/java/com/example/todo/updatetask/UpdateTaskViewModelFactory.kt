package com.example.todo.updatetask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.data.repository.TaskRepository

@Suppress("UNCHECKED_CAST")
class UpdateTaskViewModelFactory (
    private val repository: TaskRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpdateTaskViewModel(repository) as T
    }


}
