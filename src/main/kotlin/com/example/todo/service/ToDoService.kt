package com.example.todo.service

import com.example.todo.builder.TodoBuilder
import com.example.todo.model.Todo
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class ToDoService(val db: JdbcTemplate) {
    fun createToDo(name: String, desc: String?, dueDate: String, completionDate: String?, folder: String, priority: String): Todo{
        val todo = TodoBuilder()
            .name(name)
            .desc(desc)
            .dueDate(dueDate)
            .completionDate(completionDate)
            .folder(folder)
            .priority(priority)
            .build()
        db.update("insert into Todo values (? ? ? ? ? ? ?)",
            todo.id,
            todo.name,
            todo.desc,
            LocalDate.parse(todo.dueDate, DateTimeFormatter.ISO_DATE),
            LocalDate.parse(todo.completionDate, DateTimeFormatter.ISO_DATE),
            todo.folder,
            todo.priority.toString()
        )
        return todo
    }

    fun readTodo(id: String): Todo? {
        val todo = TodoBuilder().id(id)
       return db.query("select * from Todo where id = ?", id) { rs, _ ->
            todo
                .name(rs.getString("name"))
                .desc(rs.getString("desc"))
                .dueDate(rs.getDate("dueDate").toString())
                .completionDate(rs.getDate("completionDate")?.toString())
                .folder(rs.getString("folder"))
                .priority(rs.getString("priority"))
                .build()
        }.firstOrNull()
    }


}