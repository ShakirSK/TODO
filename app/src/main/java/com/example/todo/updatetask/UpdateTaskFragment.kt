package com.example.todo.updatetask

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
import com.example.todo.databinding.FragmentUpdateTaskBinding
import com.example.todo.data.repository.TaskRepository
import com.example.todo.data.local.TodoDatabase
import kotlinx.coroutines.Dispatchers

class UpdateTaskFragment : Fragment() {
    lateinit var fragmentUpdateTaskBinding :FragmentUpdateTaskBinding
    lateinit var updateTaskViewModel : UpdateTaskViewModel
    private lateinit var factory: UpdateTaskViewModelFactory
    var todoDatabase: TodoDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_update_task, container, false)

        todoDatabase = TodoDatabase.getDatabaseClient(requireContext())!!

        val repository = TaskRepository(todoDatabase!!, Dispatchers.Main)

        factory = UpdateTaskViewModelFactory(repository)

        updateTaskViewModel = ViewModelProviders.of(this, factory).get(UpdateTaskViewModel::class.java)

        fragmentUpdateTaskBinding = FragmentUpdateTaskBinding.bind(view).apply {
            this.viewmodel = updateTaskViewModel
        }

        // Set the lifecycle owner to thle ifecycle of the view
        fragmentUpdateTaskBinding.lifecycleOwner = this.viewLifecycleOwner
        return fragmentUpdateTaskBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateTaskViewModel.titleName = arguments?.getString("titlename").toString()
        updateTaskViewModel.getMainTaskTableModel()
        setupToast()
        setupNavigation()

    }
    private fun setupToast() {
        updateTaskViewModel.messageToast.observe(viewLifecycleOwner, Observer {

            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupNavigation() {
        updateTaskViewModel.taskUpdatedEvent.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { // Only proceed if the event has never been handled

                findNavController()
                        .navigate(R.id.action_updateTaskFragment_to_taskdetailsFragment2,
                                null,
                                NavOptions.Builder()
                                        .setPopUpTo(R.id.taskdetailsFragment2,
                                                true).build()
                        )
            }
        })
    }

}