package com.example.todo.model

import com.example.todo.constant.Priority

data class Todo (
    var id: String,
    var name: String,
    var desc: String?,
    var dueDate: String,
    var completionDate: String?,
    var folder: Int,
    var priority: Priority
)