package com.enigma.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigma.simpletodo.data.db.TodoDatabase
import com.enigma.simpletodo.data.model.Todo
import com.enigma.simpletodo.data.repository.TodoRepository
import com.enigma.simpletodo.databinding.ActivityMainBinding
import com.enigma.simpletodo.ui.todo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val todoRepository = TodoRepository(TodoDatabase(this))
        val viewModelFactory = TodoViewModelFactory(todoRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[TodoViewModel::class.java]

        supportFragmentManager.beginTransaction().apply {
            replace(binding.mainFragmentContainer.id, TodosFragment())
            commit()
        }

        binding.apply {
            fab.setOnClickListener {
                FormTodoBottomSheetFragment().apply {
                    show(supportFragmentManager, "Any")
                }
            }
        }
    }
}