package com.example.todo.addedtask

 import androidx.lifecycle.ViewModel
 import androidx.lifecycle.ViewModelProvider
 import com.example.todo.data.repository.TaskRepository

@Suppress("UNCHECKED_CAST")
    class AddItemViewModelFactory (
        private val repository: TaskRepository
    ) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddItemViewModel(repository) as T
    }


}
