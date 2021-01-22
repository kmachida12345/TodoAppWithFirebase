package com.example.todoappwithfirebase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String = "",
    var description: String = "",
    var updatedTime: Long = System.currentTimeMillis(),
    var dueDate: Long = -1,
    var dueTime: Long = -1,
    var isDone: Boolean = false,

) {
}