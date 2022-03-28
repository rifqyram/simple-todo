package com.enigma.simpletodo.domain.repository

import com.enigma.simpletodo.domain.db.TodoDatabase
import com.enigma.simpletodo.domain.model.Todo

class TodoRepository(val db: TodoDatabase) {

    suspend fun upsert(todo: Todo) = db.getTodoDao().upsert(todo)

    fun getTodos() = db.getTodoDao().getAllTodos()

    suspend fun deleteTodo(todo: Todo) = db.getTodoDao().deleteTodo(todo)

}