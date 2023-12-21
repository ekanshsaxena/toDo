package com.example.todo.builder

import com.example.todo.model.Folder
import com.example.todo.model.Todo
import java.util.UUID

class FolderBuilder {
    private var id: String = UUID.randomUUID().toString()
    private var name: String = "Enter a name"
    private var todos: List<Todo> = listOf()

    fun id(id: String): FolderBuilder{
        this.id = id
        return this
    }

    fun name(name: String): FolderBuilder{
        this.name = name
        return this
    }

    fun todos(todos: List<Todo>): FolderBuilder{
        this.todos = todos
        return this
    }

    fun build(): Folder{
        return Folder(
            id = this.id,
            name = this.name,
            todos = this.todos
        )
    }
}