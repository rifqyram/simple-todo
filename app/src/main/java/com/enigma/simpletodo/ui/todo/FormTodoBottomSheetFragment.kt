package com.enigma.simpletodo.ui.todo

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.enigma.simpletodo.MainActivity
import com.enigma.simpletodo.data.model.Todo
import com.enigma.simpletodo.databinding.FragmentFormTodoBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class FormTodoBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFormTodoBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TodoViewModel
    var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormTodoBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        binding.apply {
            btnSubmit.setOnClickListener {
                onSubmit()
            }
        }

        viewModel.todo.observe(viewLifecycleOwner) {
            binding.apply {
                id = it.id
                etTodo.editText?.setText(it.todo)
                cbTodo.isChecked = it.isDone
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        clearForm()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clearForm() {
        viewModel.getTodo(Todo(null, "", false))
    }

    private fun onSubmit() {
        binding.apply {
            val checked = cbTodo.isChecked
            val todoInput = etTodo.editText?.text.toString()
            val todo = Todo(id, todoInput, checked)

            if (todoInput.isBlank()) {
                etTodo.apply {
                    isErrorEnabled = true
                    error = "Todo must not be blank"
                }
            } else {
                etTodo.apply {
                    error = null
                    isErrorEnabled = false
                }

                CoroutineScope(Dispatchers.Main + Job()).launch {
                    viewModel.saveTodo(todo)
                    dismiss()
                }
            }
        }
    }
}