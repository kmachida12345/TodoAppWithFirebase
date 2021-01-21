package com.example.todoappwithfirebase.tasklist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithfirebase.MyViewModel
import com.example.todoappwithfirebase.R
import com.example.todoappwithfirebase.model.Task
import com.example.todoappwithfirebase.taskdetail.TaskDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_task_list.view.*
import kotlinx.android.synthetic.main.task_list_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private lateinit var viewAdapter: TaskListAdapter
    private val viewModel: MyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)

        val viewManager = LinearLayoutManager(context)

        viewAdapter = TaskListAdapter()

        view.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


        view.findViewById<TextView>(R.id.hoge).setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.insert(Task(0, Date(System.currentTimeMillis()).toString()))
            }
            Log.d("hoge", "onCreateView: hoge")
        }

        view.fab.setOnClickListener {
            Toast.makeText(context, "hoge", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }

        viewAdapter.setOnItemClickListener(object: TaskListAdapter.OnItemClickListener{
            override fun onItemClickListener(view: View, position: Int) {
                val action = TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment(view.task_item.text.toString(), view.task_detail.text.toString())
                findNavController().navigate(action)
            }
        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        viewModel.apply {

            getAllWords().observe(viewLifecycleOwner, {
                Log.d(
                    "hoge", "onCreateView: database modified ${viewModel.getAllWords().value}"
                )
                if (viewAdapter.itemCount <= 0) {
                    it.forEach {
                        viewAdapter.addData(it)
                    }
                } else {
                    viewAdapter.addData(it[it.lastIndex])
                }
            })
        }
    }
}