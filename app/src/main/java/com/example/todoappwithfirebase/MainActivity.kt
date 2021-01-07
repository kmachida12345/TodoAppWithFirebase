package com.example.todoappwithfirebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.AppLaunchChecker
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.todoappwithfirebase.model.AppDatabase
import com.example.todoappwithfirebase.model.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppLaunchChecker.onActivityCreate(this)
    }
}
