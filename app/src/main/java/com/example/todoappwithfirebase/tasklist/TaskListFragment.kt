package com.example.todoappwithfirebase.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todoappwithfirebase.R
import com.example.todoappwithfirebase.databinding.FragmentTaskListBinding

class TaskListFragment: Fragment() {
    private lateinit var taskListViewModel: TaskListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)

        taskListViewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)

        // でーたばいんでぃんぐの設定
        // おまじない的コード
        // バインド先のレイアウトを指定
        val binding: FragmentTaskListBinding = DataBindingUtil.setContentView(activity!!, R.layout.fragment_task_list)
        // バインド先のレイアウトが参照する変数の指定
        binding.viewModel = taskListViewModel
        // LiveDataと連携させる(双方向データバインディング実現)のため
        // レイアウトが影響されるライフサイクルを指定
        binding.lifecycleOwner = this

        return view
    }
}