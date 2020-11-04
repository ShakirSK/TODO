package com.example.todo.tasksdetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.data.repository.TaskRepository
import com.example.todo.data.local.TaskTableModel
import com.example.todo.data.local.TodoDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_taskdetails.*
import kotlinx.coroutines.Dispatchers


class TaskDetailsFragment : Fragment() {

    lateinit var taskDetailViewModel: TaskDetailViewModel
    private lateinit var taskDetailViewModelFactory : TaskDetailViewModelFactory
    var todoDatabase: TodoDatabase? = null
    lateinit var listTaskTableModel: List<TaskTableModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_taskdetails, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        todoDatabase = TodoDatabase.getDatabaseClient(requireContext())!!

        val repository = TaskRepository(todoDatabase!!, Dispatchers.Main)

        taskDetailViewModelFactory = TaskDetailViewModelFactory(repository)

        taskDetailViewModel = ViewModelProviders.of(this, taskDetailViewModelFactory).get(TaskDetailViewModel::class.java)

        taskDetailViewModel.getMainTaskTableModel()

        taskDetailViewModel.getTaskTableModel.observe(viewLifecycleOwner, Observer { taskTableModel ->

            //Adding TaskTableModel Items
            listTaskTableModel = taskTableModel

            val adapter = GroupAdapter<GroupieViewHolder>()
            adapter.addAll(listTaskTableModel.toTaskTableModelITem())
            tasks_list.adapter = adapter

            adapter.setOnItemClickListener { item, view ->
                val bundle = bundleOf("titlename" to listTaskTableModel.get(adapter.getAdapterPosition(item)).title)
                findNavController().navigate(R.id.action_taskdetailsFragment2_to_updateTaskFragment,bundle)
            }

        })

        add_task_fab.setOnClickListener {
            findNavController().navigate(R.id.action_taskdetailsFragment2_to_addTaskFragment2)
        }
    }

    fun List<TaskTableModel>.toTaskTableModelITem() : List<TaskItem>{
        return this.map {
            TaskItem(it)
        }
    }


}