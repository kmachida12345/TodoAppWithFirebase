package com.example.todoappwithfirebase

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.todoappwithfirebase.model.Task
import com.example.todoappwithfirebase.model.TaskRepository


class MyViewModel @ViewModelInject constructor(application: Application, private val mRepository: TaskRepository): AndroidViewModel(application) {

    val tasks: LiveData<List<Task>>
        get() = _tasks
    private val _tasks = mRepository.getAllTasks().asLiveData()


    // FIXME: DataBindingにかきかえ
    suspend fun insert(task: Task): Long {
        return mRepository.insert(task)
    }

}