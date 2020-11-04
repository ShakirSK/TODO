package com.example.todo.tasksdetail

import android.util.Log
import com.example.todo.R
import com.example.todo.data.local.TaskTableModel
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.task_holder_item.view.*

class TaskItem(private val maintab: TaskTableModel) : Item()  {


   /* override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        Log.d("IntroViewModel", position.toString())
        viewHolder.itemView.titlechild.text = maintab?.get(position)?.title
        viewHolder.itemView.titledescription.text = maintab?.get(position)?.description

    }*/

    override fun bind(
        viewHolder: com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder,
        position: Int
    ) {
        Log.d("IntroViewModel", position.toString())
        viewHolder.itemView.titlechild.text = maintab.title
        viewHolder.itemView.titledescription.text = maintab.description
    }

    override fun getLayout() = R.layout.task_holder_item
}
