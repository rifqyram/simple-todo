package com.enigma.simpletodo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "todos",
)
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val todo: String,
    val isDone: Boolean
)
