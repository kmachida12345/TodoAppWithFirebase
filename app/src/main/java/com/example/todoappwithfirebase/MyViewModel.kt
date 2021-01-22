package com.example.todoappwithfirebase

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todoappwithfirebase.model.Task
import com.example.todoappwithfirebase.model.TaskRepository
import javax.inject.Inject


class MyViewModel @ViewModelInject constructor(application: Application, private val mRepository: TaskRepository): AndroidViewModel(application) {

    fun getAllWords(): LiveData<List<Task>> {
        return mRepository.getAllTasks()
    }

    suspend fun insert(task: Task): Long {
        return mRepository.insert(task)
    }

    suspend fun getTask(id: Long): Task {
        return mRepository.getTask(id)
    }

}