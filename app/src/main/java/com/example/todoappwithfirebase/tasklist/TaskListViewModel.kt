package com.example.todoappwithfirebase.tasklist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.todoappwithfirebase.MainActivity
import com.example.todoappwithfirebase.data.AppDatabase
import com.example.todoappwithfirebase.data.Task
import kotlinx.coroutines.launch
import java.util.*

class TaskListViewModel: ViewModel() {

    fun onClick(){
        val hashCode = UUID.randomUUID().hashCode()

        // viewModelの生存範囲内でコルーチン開始
        viewModelScope.launch {
            Log.d("hoge", "ghaiodfa")

            val taskDao = MainActivity.db.taskDao()
            val list = taskDao.getAll()

            taskDao.insert(Task(hashCode, hashCode.toString(), "hoge", "fdad", "fdafeagt", true))

            list.stream()
                .map { task -> task.title }
                .forEach { v -> Log.d("hoge", v) }

        }
    }


}