package com.example.todoappwithfirebase.edittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todoappwithfirebase.MyViewModel
import com.example.todoappwithfirebase.R
import com.example.todoappwithfirebase.model.Task
import kotlinx.android.synthetic.main.fragment_edit_task.*
import kotlinx.android.synthetic.main.fragment_edit_task.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTaskFragment: Fragment() {

    private val viewModel: MyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_task, container, false)


        view.confirm.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.insert(Task(0, view.title.text.toString()))
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "added", Toast.LENGTH_LONG).show()
                }
                findNavController().popBackStack()
            }
        }
        return view
    }

}