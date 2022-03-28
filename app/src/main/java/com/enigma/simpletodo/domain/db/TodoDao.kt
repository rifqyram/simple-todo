package com.enigma.simpletodo.domain.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.enigma.simpletodo.domain.model.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(todo: Todo): Long

    @Query("SELECT * FROM todos")
    fun getAllTodos(): LiveData<List<Todo>>

    @Delete
    suspend fun deleteTodo(todo: Todo)
}