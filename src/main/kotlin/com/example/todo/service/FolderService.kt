package com.example.todo.service

import com.example.todo.Util.TodoUtil
import com.example.todo.builder.FolderBuilder
import com.example.todo.model.Folder
import com.example.todo.model.Todo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Service

@Service
class FolderService(
    val db: JdbcTemplate,
    val todoUtil: TodoUtil
) {
    private val logger: Logger = LoggerFactory.getLogger(FolderService::class.java)
    fun createFolder(name: String): Folder? {
        val folder: Folder = FolderBuilder().name(name).build()
        try {
            db.update("insert into Folder values (?, ?)",
                folder.id,
                folder.name
            )
        }catch (ex: Exception){
            logger.error("Error Occurred while creating Folder: $ex")
            return null
        }
        logger.info("Folder Created with ID: ${folder.id}")
        return folder
    }

    fun readFolder(id: String): Folder? {
        val folder: FolderBuilder = FolderBuilder().id(id)
        val todos: MutableList<Todo> = mutableListOf()
        try {
            var name: String? = null
            db.query("select name from Folder where id = ?", id) { rs, _ ->
                name = rs.getString("name")
            }
            name?.let { folder.name(it) } ?: run {logger.warn("Folder Not Found with the ID: $id"); return null}
            db.query("select * from Todo, Folder where Todo.id = Folder.id") { rs, _ ->
                todoUtil.getTodo(rs)?.let { todo ->
                    todos.add(todo)
                }
            }
            folder.todos(todos)
        }catch (ex: Exception){
            logger.error("Error Occurred while reading Folder: $ex")
            return null
        }
        return folder.build()
    }

    fun updateFolder(id: String, name: String) : Folder?{
        if(readFolder(id) == null) {
            logger.info("Folder not found with the ID: $id")
            return null
        }
        try {
            db.update("update Folder set name=? where id = ?", name, id)
            return readFolder(id)
        }catch (ex: Exception){
            logger.error("Error Occurred while updating Folder: $ex")
            return null
        }
    }

    fun deleteFolder(id: String): Boolean {
        try {
            db.update("delete from Folder where id = ?", id)
            logger.info("Folder Deleted Successfully!")
        }catch (ex: Exception){
            logger.error("Error Occurred while deleting Folder: $ex")
            return false
        }
        return true
    }
}