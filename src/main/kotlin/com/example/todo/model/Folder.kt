package com.example.todo.model

data class Folder (
    var id: String,
    var name: String,
    var todos: List<Todo>
)