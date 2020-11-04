package com.example.todo.addedtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.data.repository.TaskRepository
import com.example.todo.data.local.TaskTableModel
import com.example.todo.Coroutines
import com.example.todo.Event
import kotlinx.coroutines.Job

class  AddItemViewModel(private val tasksRepository: TaskRepository) :ViewModel(){
    lateinit var job : Job
    var title = MutableLiveData<String>()
    var description = MutableLiveData<String>()

    private val _taskUpdatedEvent = MutableLiveData<Event<String>>()
    val taskUpdatedEvent: LiveData<Event<String>> = _taskUpdatedEvent

    private val _messageToast = MutableLiveData<String>()
    val messageToast: LiveData<String> = _messageToast


    fun saveTask(){

        val currentTitle = title.value
        val currentDescription = description.value

        if (currentTitle == null || currentDescription == null) {
            _messageToast.value = "Fields cannot be empty"
             return
        }

        job = Coroutines.ioThenMain(
            {
                tasksRepository.saveTask(TaskTableModel(currentTitle.toString(), currentDescription.toString()))
            },
            {
                _messageToast.value = "Successfully Added"
                _taskUpdatedEvent.value = Event("Unit")
            }
        )
    }


}