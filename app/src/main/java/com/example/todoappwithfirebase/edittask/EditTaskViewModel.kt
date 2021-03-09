package com.example.todoappwithfirebase.edittask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoappwithfirebase.model.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(application: Application, private val mRepository: TaskRepository): AndroidViewModel(application) {
    val title = MutableLiveData("")
    val desc = MutableLiveData("")

    fun insert() {

    }
}