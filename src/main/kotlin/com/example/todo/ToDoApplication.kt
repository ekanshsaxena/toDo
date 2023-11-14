package com.example.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ToDoApplication

fun main(args: Array<String>) {
	runApplication<ToDoApplication>(*args)
}
