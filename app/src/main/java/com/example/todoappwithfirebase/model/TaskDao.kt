package com.example.todoappwithfirebase.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task : Task)

    @Update
    suspend fun update(task : Task)

    @Delete
    fun delete(task : Task)

    @Query("delete from tasks")
    fun deleteAll()

    @Query("select * from tasks")
    fun getAll(): LiveData<List<Task>>

    @Query("select * from tasks where id = :id")
    fun getTask(id: Int): Task

}