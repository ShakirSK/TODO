package com.example.todo.tasksdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.data.repository.TaskRepository
import com.example.todo.data.local.TaskTableModel
import com.example.todo.Coroutines
import kotlinx.coroutines.Job

class  TaskDetailViewModel(private val tasksRepository: TaskRepository) : ViewModel(){

    lateinit var job : Job
    private val _getTaskTableModel = MutableLiveData<List<TaskTableModel>>()

    //getTaskTableModel is a  LiveData data holder for Football
    val getTaskTableModel: MutableLiveData<List<TaskTableModel>>
        get() = _getTaskTableModel



    //used kotlin coroutines
    //which fetch data inside an IOthread and then we have a callback to the mainthread
    fun getMainTaskTableModel() {
          job = Coroutines.ioThenMain(
            { tasksRepository.getTasks() },
            {
                _getTaskTableModel.value = it
            }
        )
    }


}