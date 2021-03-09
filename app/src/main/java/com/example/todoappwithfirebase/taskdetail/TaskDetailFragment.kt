package com.example.todoappwithfirebase.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.todoappwithfirebase.MyViewModel
import com.example.todoappwithfirebase.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_task_detail.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskDetailFragment: Fragment() {

    val args: TaskDetailFragmentArgs by navArgs()
    val viewModel: MyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_detail, container, false)

//        lifecycleScope.launch(Dispatchers.IO) {
//            val task = viewModel.getTask(args.taskId)
//            launch(Dispatchers.Main) {
//                view?.task_title?.text = task.title
//            }
//        }

        return view
    }
}