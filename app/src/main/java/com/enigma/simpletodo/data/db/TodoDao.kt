package com.enigma.simpletodo.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.enigma.simpletodo.data.model.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(todo: Todo): Long

    @Query("SELECT * FROM todos")
    suspend fun getAllTodos(): List<Todo>

    @Delete
    suspend fun deleteTodo(todo: Todo)
}