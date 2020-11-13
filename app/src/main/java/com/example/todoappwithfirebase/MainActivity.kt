package com.example.todoappwithfirebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.todoappwithfirebase.model.AppDatabase
import com.example.todoappwithfirebase.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name")
                .build()
        val taskDao = database.taskDao()
        val task = Task(0, Date().time.toString())
        lifecycleScope.launch(Dispatchers.IO) {
            taskDao.insert(task)
            Log.v("TAG", "after insert ${taskDao.getAll()}")
        }
    }
}
