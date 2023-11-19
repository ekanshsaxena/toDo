package com.example.todo.service

import com.example.todo.constant.Priority
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class ToDoService(val db: JdbcTemplate) {
    fun createToDo(name: String, desc: String?, dueDate: String, completionDate: String?, folder: String, priority: Priority){
        // TODO::
    }
}