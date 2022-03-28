package com.enigma.simpletodo.ui.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigma.simpletodo.R
import com.enigma.simpletodo.databinding.FragmentTodosBinding
import com.enigma.simpletodo.domain.model.Todo
import com.enigma.simpletodo.ui.adapter.TodoAdapter

class TodosFragment : Fragment() {
    private var _binding: FragmentTodosBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        todoAdapter.differ.submitList(listOf(
            Todo(1, "Makan", false),
            Todo(2, "Ngoding", true),
            Todo(3, "Tidur", false)
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter()
        binding.apply {
            rvTodo.apply {
                adapter = todoAdapter
                layoutManager = LinearLayoutManager(activity)
            }
        }
    }

}