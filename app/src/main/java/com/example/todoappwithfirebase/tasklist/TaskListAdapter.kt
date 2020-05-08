package com.example.todoappwithfirebase.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithfirebase.R
import com.example.todoappwithfirebase.data.Task
class TaskListAdapter(
    private val taskList: ArrayList<Task>
) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView
        val name: TextView
        val age: TextView
        val button: Button

        init {
            image = view.findViewById(R.id.image)
            name = view.findViewById(R.id.name)
            age = view.findViewById(R.id.age)
            button = view.findViewById(R.id.button)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.tasklist_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val animal = taskList[position]

        viewHolder.name.text = position.toString()
        viewHolder.age.text = "hoge"
    }

    override fun getItemCount() = taskList.size
}
