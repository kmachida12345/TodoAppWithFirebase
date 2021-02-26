package com.example.todoappwithfirebase.model

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task : Task): Long

    @Update
    suspend fun update(task : Task)

    @Delete
    fun delete(task : Task)

    @Query("delete from tasks")
    fun deleteAll()

    @Query("select * from tasks")
    fun getAll(): Flow<List<Task>>

    @Query("select * from tasks where id = :id")
    suspend fun getTask(id: Long): Task

}