package com.enigma.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enigma.simpletodo.databinding.ActivityMainBinding
import com.enigma.simpletodo.ui.todo.FormTodoBottomSheetFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            fab.setOnClickListener {
                FormTodoBottomSheetFragment().apply {
                    show(supportFragmentManager, "Any")
                }
            }
        }
    }
}