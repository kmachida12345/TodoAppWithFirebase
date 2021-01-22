package com.example.todoappwithfirebase.model

import androidx.lifecycle.LiveData
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val database: AppDatabase
) {
    private val taskDao: TaskDao by lazy { database.taskDao() }

    suspend fun insert(task: Task): Long {
        return taskDao.insert(task)
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAll()
    }

    suspend fun getTask(id: Long): Task {
        return taskDao.getTask(id)
    }

}