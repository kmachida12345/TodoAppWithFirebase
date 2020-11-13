package com.example.todoappwithfirebase.model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room

class TaskRepository(application: Application) {
    private val taskDao: TaskDao

    init {
        val database =
                Room.databaseBuilder(application, AppDatabase::class.java, "database-name")
                        .build()
        taskDao = database.taskDao()
    }

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAll()
    }

}