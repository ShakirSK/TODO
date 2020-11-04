package com.example.todo.addedtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentAddTaskBinding
import com.example.todo.data.repository.TaskRepository
import com.example.todo.data.local.TodoDatabase
import kotlinx.coroutines.Dispatchers


class AddTaskFragment : Fragment() {

    lateinit var fragmentAddTaskBinding : FragmentAddTaskBinding
    lateinit var addItemViewModel : AddItemViewModel
    private lateinit var addItemViewModelFactory : AddItemViewModelFactory
    var todoDatabase : TodoDatabase? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_task, container, false)


        todoDatabase = TodoDatabase.getDatabaseClient(requireContext())!!

        val repository = TaskRepository(todoDatabase!!, Dispatchers.Main)

        addItemViewModelFactory = AddItemViewModelFactory(repository)

        addItemViewModel = ViewModelProviders.of(this, addItemViewModelFactory).get(AddItemViewModel::class.java)

        fragmentAddTaskBinding = FragmentAddTaskBinding.bind(view).apply {
            this.viewmodel = addItemViewModel
        }

        fragmentAddTaskBinding.lifecycleOwner = this.viewLifecycleOwner
        return fragmentAddTaskBinding.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
         setupToast()
         setupNavigation()
    }

    private fun setupToast() {
        addItemViewModel.messageToast.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupNavigation() {
        addItemViewModel.taskUpdatedEvent.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                findNavController()
                        .navigate(R.id.action_addTaskFragment2_to_taskdetailsFragment2,
                                null,
                                NavOptions.Builder()
                                        .setPopUpTo(R.id.taskdetailsFragment2,
                                                true).build()
                        )
            }
        })
    }


}