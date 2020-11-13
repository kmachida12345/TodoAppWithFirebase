package com.example.todoappwithfirebase.model

import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    fun insert(task : Task)

    @Update
    fun update(task : Task)

    @Delete
    fun delete(task : Task)

    @Query("delete from tasks")
    fun deleteAll()

    @Query("select * from tasks")
    fun getAll(): List<Task>

    @Query("select * from tasks where id = :id")
    fun getTask(id: Int): Task

}