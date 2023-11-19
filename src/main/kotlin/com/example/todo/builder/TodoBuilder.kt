package com.example.todo.builder

import com.example.todo.constant.Priority
import com.example.todo.model.Todo
import java.util.UUID

class TodoBuilder {
    private var id: String = UUID.randomUUID().toString()
    private var name: String = "Enter a name"
    private var desc: String? = null
    private var dueDate: String = "2030-12-31"
    private var completionDate: String? = null
    private var folder: String = "Unknown"
    private var priority: Priority = Priority.HIGH

    fun name(name: String): TodoBuilder{
        this.name = name
        return this
    }

    fun desc(desc: String?): TodoBuilder{
        this.desc = desc
        return this
    }

    fun dueDate(dueDate: String): TodoBuilder{
        this.dueDate = dueDate
        return this
    }

    fun completionDate(completionDate: String?): TodoBuilder{
        this.completionDate = completionDate
        return this
    }

    fun folder(folder: String): TodoBuilder{
        this.folder = folder
        return this
    }

    fun priority(priority: String): TodoBuilder{
        when(priority){
            "HIGH" -> this.priority = Priority.HIGH
            "MED" -> this.priority = Priority.MED
            else -> this.priority = Priority.LOW
        }
        return this
    }

    fun build() : Todo{
        return Todo(
            id = this.id,
            name = this.name,
            desc = this.desc,
            dueDate = this.dueDate,
            completionDate = this.completionDate,
            folder = this.folder,
            priority = this.priority
        )
    }
}