package com.example.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

// to hold the list to store all the todos
// to have a view holder

// primary constructor to store all the todos data datatype
// in mutable list
class TodoAdapter(private val todos: ArrayList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    // Base class to use as obj in recyclerview and inherited from viewHolder
    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    // basic function to override to apply in the console
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll(todo ->
            todo.isChecked
        )
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    // same as like __getitem__ and __len__ magic functions in python
    // used to return data for the necessary request from the viewholder
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = currTodo.title
            cbDone.isChecked = currTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, currTodo.isChecked)
            cbDone.setOnCheckedChangeListner { _, isCheked ->
                toggleStrikeThrough(tvTodoTitle, isCheked)
                currTodo.isChecked = !currTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

}