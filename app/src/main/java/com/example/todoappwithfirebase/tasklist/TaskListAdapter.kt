package com.example.todoappwithfirebase.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithfirebase.R
import com.example.todoappwithfirebase.databinding.TaskListItemBinding
import com.example.todoappwithfirebase.model.Task

class TaskListAdapter: RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>() {

    private val tasks = arrayListOf<Task>() // Cached copy of words
    private val myDataset = arrayListOf<Task>()

    class TaskListViewHolder(val binding: TaskListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        // create a new view
        val binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.task_list_item, parent, false) as TaskListItemBinding

        return TaskListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.binding.task = myDataset[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    fun addData(task: Task) {
        myDataset.add(task)
        notifyDataSetChanged()
    }

}