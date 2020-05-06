package com.example.todoappwithfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoappwithfirebase.data.AppDatabase

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase.getInstance(applicationContext)
    }
}
