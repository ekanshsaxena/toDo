package com.example.todo.builder

import com.example.todo.constant.Priority
import java.util.UUID

class TodoBuilder {
    private var id: String = UUID.randomUUID().toString()
    private var name: String = "Enter a name"
    private var desc: String? = null
    private var dueDate: String = "2030-12-31"
    private var completionDate: String? = null
    private var folder: String = "Unknown"
    private var priority: Priority = Priority.HIGH

    //TODO: Write functions to set the attributes and the build function.
}