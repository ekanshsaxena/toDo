package com.example.todo.service

import com.example.todo.Util.TodoUtil
import com.example.todo.model.Todo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class ToDoService(
    val db: JdbcTemplate,
    val todoUtil: TodoUtil
) {
    private val logger: Logger = LoggerFactory.getLogger(ToDoService::class.java)
    fun createTodo(todo: Todo): Todo? {
        todo.id = UUID.randomUUID().toString()
        try {
            db.update("insert into Todo values (?, ?, ?, ?, ?, ?, ?)",
                todo.id,
                todo.name,
                todo.desc,
                LocalDate.parse(todo.dueDate, DateTimeFormatter.ISO_DATE),
                LocalDate.parse(todo.completionDate, DateTimeFormatter.ISO_DATE),
                todo.folder,
                todo.priority.toString()
            )
        }catch (ex: Exception){
            logger.error("Error Occurred while creating Todo: $ex")
            return null
        }
        return todo
    }

    fun readTodo(id: String): Todo? {
        return try {
            var todo: Todo? = null
            db.query("select * from Todo where id = ?", id) { rs, _ ->
                todo = todoUtil.getTodo(rs)
            }
            if (todo == null){
                logger.warn("Todo Not Found with the ID: $id")
            }
            return todo
        }catch (ex: Exception){
            logger.error("Error Occurred while reading Todo: $ex")
            null
        }
    }

    fun updateTodo(id: String, todo: Todo) : Todo?{
        if(readTodo(id) == null) {
            logger.info("Todo not found with the ID: $id")
            return null
        }
        try {
            db.update("update Todo set name=?, desc=?, due_date=?, completion_date=?, folder=?, priority=? where id = ?", todo.name, todo.desc, LocalDate.parse(todo.dueDate, DateTimeFormatter.ISO_DATE),
                LocalDate.parse(todo.completionDate, DateTimeFormatter.ISO_DATE), todo.folder, todo.priority.toString(), id)
        }catch (ex: Exception){
            logger.error("Error Occurred while updating Todo: $ex")
            return null
        }
        return todo
    }

    fun deleteTodo(id: String): Boolean {
        try {
            db.update("delete from Todo where id = ?", id)
        }catch (ex: Exception){
            logger.error("Error Occurred while deleting Todo: $ex")
            return false
        }
        return true
    }
}