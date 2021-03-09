package com.example.todoappwithfirebase.taskdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoappwithfirebase.model.Task
import com.example.todoappwithfirebase.model.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val _application: Application,
    private val mRepository: TaskRepository
) :
    AndroidViewModel(_application) {

    val task = MutableLiveData<Task>()

    fun fetchTask(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            task.postValue(mRepository.getTask(id))
            Timber.d("hoge task=${task.value}")
        }
    }

}