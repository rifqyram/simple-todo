package com.enigma.simpletodo.ui.todo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigma.simpletodo.data.model.Todo
import com.enigma.simpletodo.data.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(private val todoRepository: TodoRepository) : ViewModel() {
    private var _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> get() = _todos

    private var _todo = MutableLiveData<Todo>()
    val todo: LiveData<Todo> get() = _todo

    init {
        viewModelScope.launch {
            getAllTodos()
        }
    }

    suspend fun saveTodo(todo: Todo) = viewModelScope.launch {
        todoRepository.upsert(todo)
        getAllTodos()
    }

    fun getTodo(todo: Todo) {
        _todo.postValue(todo)
    }

    suspend fun getAllTodos() = viewModelScope.launch {
        val todos = todoRepository.getTodos()
        _todos.postValue(todos)
    }

    suspend fun deleteTodo(todo: Todo) = viewModelScope.launch {
        todoRepository.deleteTodo(todo)
        getAllTodos()
    }

}

