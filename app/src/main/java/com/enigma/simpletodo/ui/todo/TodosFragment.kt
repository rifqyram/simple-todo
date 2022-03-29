package com.enigma.simpletodo.ui.todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enigma.simpletodo.MainActivity
import com.enigma.simpletodo.databinding.FragmentTodosBinding
import com.enigma.simpletodo.ui.adapter.TodoAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class TodosFragment : Fragment() {
    private var _binding: FragmentTodosBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var viewModel: TodoViewModel

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
        viewModel = (activity as MainActivity).viewModel

        todoAdapter.setOnItemClickListener {
            viewModel.getTodo(it)
            val supportFragmentManager = (activity as MainActivity).supportFragmentManager

            FormTodoBottomSheetFragment().apply {
                show(supportFragmentManager, "Any")
            }
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val todo = todoAdapter.differ.currentList[position]
                lifecycleScope.launch {
                    viewModel.deleteTodo(todo)
                }
                Snackbar.make(view, "Successfully delete todo ${todo.todo}", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo") {
                            lifecycleScope.launch {
                                viewModel.saveTodo(todo)
                            }
                        }
                        show()
                    }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvTodo)
        }

        viewModel.todos.observe(viewLifecycleOwner) {
            todoAdapter.differ.submitList(it)
        }
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