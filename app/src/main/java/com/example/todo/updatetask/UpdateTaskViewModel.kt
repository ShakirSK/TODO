package com.example.todo.updatetask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.data.repository.TaskRepository
import com.example.todo.Coroutines
import com.example.todo.Event
import kotlinx.coroutines.Job


class  UpdateTaskViewModel(private val tasksRepository: TaskRepository) : ViewModel(){
    lateinit var job : Job
    var title = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var titleName :String =""

    private val _taskUpdatedEvent = MutableLiveData<Event<String>>()
    val taskUpdatedEvent: LiveData<Event<String>> = _taskUpdatedEvent

    private val _messageToast = MutableLiveData<String>()
    val messageToast: LiveData<String> = _messageToast


    fun updateTask(){

        val currentTitle = title.value
        val currentDescription = description.value

        if (currentTitle == null || currentDescription == null) {
            _messageToast.value = "Fields cannot be empty"
            return
        }

        job = Coroutines.ioThenMain(
            { tasksRepository.updateTask(titleName,currentTitle.toString(),currentDescription.toString())
            },
            {
                 _messageToast.value = "Successfully Updated"
                _taskUpdatedEvent.value = Event("Unit")
            }
        )
    }

    //used kotlin coroutines
    //which fetch data inside an IOthread and then we have a callback to the mainthread
    fun getMainTaskTableModel() {
        job = Coroutines.ioThenMain(
            { tasksRepository.getTasksByTitle(titleName) },
            {
                title.value = it?.title
                description.value = it?.description
            }
        )
    }




}