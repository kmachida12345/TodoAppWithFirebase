package com.example.todoappwithfirebase.edittask

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todoappwithfirebase.MyViewModel
import com.example.todoappwithfirebase.R
import com.example.todoappwithfirebase.databinding.FragmentEditTaskBinding
import com.example.todoappwithfirebase.model.Task
import com.example.todoappwithfirebase.reminder.AlarmReceiver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class EditTaskFragment: Fragment() {

    private val viewModel: EditTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditTaskBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        // FIXME: DataBindingにかきかえ
//        binding.root.findViewById<Button>(R.id.confirm).setOnClickListener {
//            lifecycleScope.launch(Dispatchers.IO) {
//
//                val calNow: Calendar = Calendar.getInstance()
//                val calSet: Calendar = calNow.clone() as Calendar
//
//                calSet.set(Calendar.HOUR_OF_DAY, binding.root.findViewById<TimePicker>(R.id.remind_time_picker).hour)
//                calSet.set(Calendar.MINUTE, binding.root.findViewById<TimePicker>(R.id.remind_time_picker).minute)
//                calSet.set(Calendar.SECOND, 0)
//                calSet.set(Calendar.MILLISECOND, 0)
//
////                if (calSet.compareTo(calNow) <= 0) {
////                    // Today Set time passed, count to tomorrow
////                    calSet.add(Calendar.DATE, 1)
////                }
//
//                val task = Task(0, view.findViewById<TextView>(R.id.title).text.toString(), dueTime = calSet.timeInMillis)
//
////                val id = viewModel.insert(task)
//
//                val alarmManager =
//                    requireContext().getSystemService(ALARM_SERVICE) as? AlarmManager
//
//                val alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
//                    intent.putExtra("taskId", id)
//                    PendingIntent.getBroadcast(context, 0, intent, FLAG_ONE_SHOT)
//                }
//                alarmManager?.set(AlarmManager.RTC, task.dueTime, alarmIntent)
//
//                launch(Dispatchers.Main) {
//                    Toast.makeText(context, "added", Toast.LENGTH_LONG).show()
//                }
//                findNavController().popBackStack()
//            }
//        }
        return binding.root
    }

}