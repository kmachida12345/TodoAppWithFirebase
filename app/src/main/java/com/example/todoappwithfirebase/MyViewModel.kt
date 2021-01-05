package com.example.todoappwithfirebase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todoappwithfirebase.model.Task
import com.example.todoappwithfirebase.model.TaskRepository


class MyViewModel(application: Application): AndroidViewModel(application) {

    private var mRepository: TaskRepository = TaskRepository(application)

    var mAllWords: LiveData<List<Task>>

    init {
        mAllWords = mRepository.getAllTasks()
    }

    fun getAllWords(): LiveData<List<Task>> {
        return mAllWords
    }

    suspend fun insert(task: Task) {
        mRepository.insert(task)
    }

}