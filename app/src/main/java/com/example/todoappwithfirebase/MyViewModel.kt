package com.example.todoappwithfirebase

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoappwithfirebase.model.Task
import com.example.todoappwithfirebase.model.TaskRepository


class MyViewModel @ViewModelInject constructor(application: Application, private val mRepository: TaskRepository): AndroidViewModel(application) {


    val allTasks: LiveData<List<Task>>
        get() = _allTasks
    private val _allTasks = MutableLiveData()

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