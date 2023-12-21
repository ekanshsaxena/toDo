package com.example.todo.controller

import com.example.todo.model.Todo
import com.example.todo.service.ToDoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
class ToDoController(val toDoService: ToDoService) {

    private val logger: Logger = LoggerFactory.getLogger(ToDoController::class.java)
    @PostMapping("/createTodo")
    fun createTodo(@RequestBody todo: Todo): Todo?{
        val newTodo : Todo? = toDoService.createTodo(todo)
        if (newTodo == null){
            logger.warn("Todo not created")
        }
        return newTodo
    }

    @GetMapping("/readTodo/{id}")
    fun readTodo(@PathVariable id: String): Todo?{
        return toDoService.readTodo(id)
    }

    @PutMapping("/updateTodo/{id}")
    fun updateTodo(@PathVariable id: String, @RequestBody todo: Todo): Todo?{
        return toDoService.updateTodo(id, todo)
    }

    @DeleteMapping("/deleteTodo/{id}")
    fun deleteTodo(@PathVariable id: String): Boolean{
        return toDoService.deleteTodo(id)
    }
}