package com.example.todoappwithfirebase.tasklist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithfirebase.MyViewModel
import com.example.todoappwithfirebase.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_task_list.view.*

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

        view.fab.setOnClickListener {
            Toast.makeText(context, "hoge", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }

        viewAdapter.setOnItemClickListener(object : TaskListAdapter.OnItemClickListener {
            override fun onItemClickListener(id: Long) {
                val action = TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment(id)
                findNavController().navigate(action)
            }
        })

        return view
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