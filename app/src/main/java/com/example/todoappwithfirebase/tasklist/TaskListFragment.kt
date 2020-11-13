package com.example.todoappwithfirebase.tasklist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithfirebase.MyViewModel
import com.example.todoappwithfirebase.R
import com.example.todoappwithfirebase.databinding.FragmentTaskListBinding
import com.example.todoappwithfirebase.model.Task
import kotlinx.android.synthetic.main.fragment_task_list.*
import kotlinx.android.synthetic.main.task_list_item.*
import kotlinx.android.synthetic.main.task_list_item.task_item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class TaskListFragment : Fragment() {

    private lateinit var viewAdapter: TaskListAdapter
    private val viewModel: MyViewModel by viewModels()
//    private lateinit var binding: FragmentTaskListBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false)

        val viewManager = LinearLayoutManager(context)

        viewAdapter = TaskListAdapter()

        view.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        viewModel.apply {

            getAllWords().observe(viewLifecycleOwner, {
                Log.d("hoge", "onCreateView: database modified HOGE ${viewModel.getAllWords().value}")
            })
        }

        view.findViewById<TextView>(R.id.hoge).setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.insert(Task(0, Date(System.currentTimeMillis()).toString()))
            }
            Log.d("hoge", "onCreateView: hoge")
        }


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //データをSTARTED かRESUMED状態である場合にのみ、アップデートするように、LifecycleOwnerを紐付け、ライフサイクル内にオブザーバを追加
        viewModel.mAllWords.observe(viewLifecycleOwner, {
            viewAdapter.addData(task = it[0])
        })
    }
}