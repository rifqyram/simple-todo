package com.enigma.simpletodo.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.enigma.simpletodo.R
import com.enigma.simpletodo.databinding.TodoItemBinding
import com.enigma.simpletodo.data.model.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = TodoItemBinding.bind(itemView)

        fun bind(todo: Todo) {
            binding.apply {
                tvTodo.text = todo.todo
                cbTodo.isChecked = todo.isDone
            }
            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(todo)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = differ.currentList[position]
        holder.bind(todo)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((Todo) -> Unit)? = null

    fun setOnItemClickListener(listener: (Todo) -> Unit) {
        onItemClickListener = listener
    }
}