package com.example.todoappwithfirebase.edittask

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoappwithfirebase.model.Task
import com.example.todoappwithfirebase.model.TaskRepository
import com.example.todoappwithfirebase.reminder.AlarmReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val _application: Application,
    private val mRepository: TaskRepository
) : AndroidViewModel(_application) {
    val title = MutableLiveData("")
    val desc = MutableLiveData("")
    private val alarmManager: AlarmManager =
        getSystemService(_application, AlarmManager::class.java)!!

    /** メッセージを送出する [Flow]。 */
    val message: SharedFlow<Message>
        get() = _message
    private val _message = MutableSharedFlow<Message>()

    fun insert() {
        val calNow: Calendar = Calendar.getInstance()
        val calSet: Calendar = calNow.clone() as Calendar

        val task = Task(
            0,
            title.value ?: "",
            description = desc.value ?: "",
            dueTime = calSet.timeInMillis
        )

        Timber.d("hoge ${title.value}, ${desc.value}")

        viewModelScope.launch(Dispatchers.Main) {
            val id = mRepository.insert(task)

            val alarmIntent = Intent(_application, AlarmReceiver::class.java).let { intent ->
                intent.putExtra("taskId", id)
                PendingIntent.getBroadcast(_application, 0, intent, FLAG_ONE_SHOT)
            }
            alarmManager.set(AlarmManager.RTC, task.dueTime, alarmIntent)

            _message.emit(Message.Done)

        }
    }

    sealed class Message {
        object Done : Message()
    }

}