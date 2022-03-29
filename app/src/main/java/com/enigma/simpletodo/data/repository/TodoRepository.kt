package com.enigma.simpletodo.data.repository

import com.enigma.simpletodo.data.db.TodoDatabase
import com.enigma.simpletodo.data.model.Todo

class TodoRepository(private val db: TodoDatabase) {

    suspend fun upsert(todo: Todo) = db.getTodoDao().upsert(todo)

    suspend fun getTodos() = db.getTodoDao().getAllTodos()

    suspend fun deleteTodo(todo: Todo) = db.getTodoDao().deleteTodo(todo)

}