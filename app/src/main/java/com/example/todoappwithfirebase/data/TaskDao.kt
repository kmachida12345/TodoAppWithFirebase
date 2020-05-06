package com.example.todoappwithfirebase.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<Task>

    @Query("SELECT * FROM tasks WHERE taskId IN (:taskIds)")
    suspend fun loadAllByIds(taskIds: IntArray): List<Task>

    @Insert
    suspend fun insertAll(vararg tasks: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)
}
