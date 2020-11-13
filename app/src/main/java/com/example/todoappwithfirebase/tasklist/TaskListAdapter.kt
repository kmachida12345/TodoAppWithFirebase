package com.example.todoappwithfirebase.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithfirebase.R

class TaskListAdapter(private val myDataset: Array<String>) : RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>() {


    class TaskListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.task_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.task_list_item, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return TaskListViewHolder(textView)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = myDataset[position]
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }
}