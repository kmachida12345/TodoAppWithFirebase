package com.example.todoappwithfirebase.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.todoappwithfirebase.databinding.FragmentTaskDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TaskDetailFragment: Fragment() {

    val args: TaskDetailFragmentArgs by navArgs()
    val viewModel: TaskDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTaskDetailBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        Timber.d("hoge args=$args")


//        lifecycleScope.launch(Dispatchers.IO) {
//            val task = viewModel.getTask(args.taskId)
//            launch(Dispatchers.Main) {
//                view?.task_title?.text = task.title
//            }
//        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchTask(args.taskId)
    }
}