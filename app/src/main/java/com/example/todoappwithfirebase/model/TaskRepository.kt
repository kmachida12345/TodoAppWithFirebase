package com.example.todoappwithfirebase.model

import androidx.lifecycle.LiveData
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val database: AppDatabase
) {
    private val taskDao: TaskDao by lazy { database.taskDao() }

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAll()
    }

}