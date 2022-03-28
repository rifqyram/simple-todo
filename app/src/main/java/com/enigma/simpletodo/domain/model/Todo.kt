package com.enigma.simpletodo.domain.model

import androidx.room.Entity

@Entity(
    tableName = "todos",
)
data class Todo(
    val id: Int? = null,
    val todo: String,
    val isDone: Boolean
)
