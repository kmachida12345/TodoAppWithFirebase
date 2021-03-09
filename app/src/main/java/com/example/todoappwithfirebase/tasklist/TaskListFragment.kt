package com.example.todoappwithfirebase.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithfirebase.MyViewModel
import com.example.todoappwithfirebase.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

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



            val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val fromPosition = viewHolder.adapterPosition
                    val toPosition = target.adapterPosition

                    this@apply.adapter?.notifyItemMoved(fromPosition, toPosition)

                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewHolder.let {
                        this@apply.adapter?.notifyItemRemoved(viewHolder.adapterPosition)
                    }
                }

            })

            itemTouchHelper.attachToRecyclerView(this)
        }

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tasks.observe(viewLifecycleOwner) { list ->
            if (viewAdapter.itemCount <= 0) {
                list.forEach {
                    viewAdapter.addData(it)
                }
            } else {
                viewAdapter.addData(list[list.lastIndex])
            }
        }
    }
}