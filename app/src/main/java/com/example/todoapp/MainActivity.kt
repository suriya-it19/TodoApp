package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(ArrayList())

        rvTodo.adapter =todoAdapter
        rvTodo.layoutManager = LinearLayoutManager(this)

        btAddTodo.setOnClickListener() {
            val todoTitle = etTodo.text.toString()
            if(todoTitle.isEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodo.text.clear()
            }
        }
        btRemoveTodo.setOnClickListener() {
            todoAdapter.deleteDoneTodos()
        }
    }
}