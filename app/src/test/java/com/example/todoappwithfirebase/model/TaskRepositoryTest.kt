package com.example.todoappwithfirebase.model

import android.os.Build
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1], application = HiltTestApplication::class)
class TaskRepositoryTest {

    @Inject
    @Named("task.db")
    lateinit var database: AppDatabase
    private lateinit var userDao: TaskDao


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertTask() = runBlockingTest {
        val task = Task(
            title = "title test",
            description = "desc test",
            id = 114514
        )
        userDao.insert(task)
        val taskList = mutableListOf<Task>()
        val job = launch {
            userDao.getAll().collect {
                it.forEach {task ->
                    taskList.add(task)
                }
            }
        }
        job.cancel()

        assertEquals(true, taskList.contains(task))
    }


    @Before
    fun setup() {
        hiltRule.inject()
        userDao = database.taskDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    // テスト用util
    suspend fun <T> Flow<T>.toList(
        block: suspend CoroutineScope.() -> Unit
    ): List<T> {
        val list = mutableListOf<T>()
        coroutineScope {
            val job = launch {
                this@toList.collect {
                    list.add(it)
                }
            }
            block.invoke(this)
            job.cancel()
        }
        return list
    }
}