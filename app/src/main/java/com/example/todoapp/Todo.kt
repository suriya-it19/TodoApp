package com.example.todoapp

// data class like structs/user defined data type in C
// to mold a set of data as a single data, for associated
// data processing
data class Todo(
    var title: String = "Todo App",
    var isChecked: Boolean = false
)