package com.enigma.simpletodo.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigma.simpletodo.data.repository.TodoRepository

class TodoViewModelFactory(private val todoRepository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoViewModel(todoRepository) as T
    }
}