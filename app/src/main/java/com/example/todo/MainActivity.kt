package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.fragment)

        // setting title according to fragment
        navController.addOnDestinationChangedListener {
                controller, destination, arguments ->
            toolbar.title = navController.currentDestination?.label
            toolbar.title  = when (destination.id) {
                R.id.taskdetailsFragment2 -> "TODO MY TASK"
                R.id.addTaskFragment2 -> "ADD NEW TASK"
                R.id.updateTaskFragment -> "UPDATE TASK"
                else -> "Default title"
            }
        }

    }
}
