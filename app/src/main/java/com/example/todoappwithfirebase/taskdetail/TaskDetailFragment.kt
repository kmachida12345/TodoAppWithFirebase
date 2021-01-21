package com.example.todoappwithfirebase.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.todoappwithfirebase.R
import kotlinx.android.synthetic.main.fragment_task_detail.view.*

class TaskDetailFragment: Fragment() {

    val args: TaskDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_detail, container, false)

        view.task_title.text = args.taskTitle
        view.task_detail.text = args.taskDetail

        return view
    }
}