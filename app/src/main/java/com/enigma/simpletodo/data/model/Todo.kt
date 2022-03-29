package com.enigma.simpletodo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "todos",
)
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val todo: String,
    val isDone: Boolean
)
