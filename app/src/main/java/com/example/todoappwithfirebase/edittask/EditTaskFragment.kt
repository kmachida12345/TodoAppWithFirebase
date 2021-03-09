package com.example.todoappwithfirebase.edittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todoappwithfirebase.databinding.FragmentEditTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class EditTaskFragment : Fragment() {

    private val viewModel: EditTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditTaskBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        viewModel.message.onEach {
            findNavController().popBackStack()
        }.launchIn(lifecycleScope)

        return binding.root
    }

}