package com.example.todo.Util

import com.example.todo.builder.TodoBuilder
import com.example.todo.model.Todo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class TodoUtil {
    private val logger: Logger = LoggerFactory.getLogger(TodoUtil::class.java)
    fun getTodo(response: ResultSet): Todo?{
        try {
            return TodoBuilder()
                .id(response.getString("id"))
                .name(response.getString("name"))
                .desc(response.getString("desc"))
                .dueDate(response.getDate("due_date").toString())
                .completionDate(response.getDate("completion_date")?.toString())
                .folder(response.getString("folder"))
                .priority(response.getString("priority"))
                .build()
        }catch (ex: Exception){
            logger.error("Error Occurred while fetching Todo from DB", ex)
        }
        return null
    }
}