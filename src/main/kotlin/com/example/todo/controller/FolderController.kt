package com.example.todo.controller

import com.example.todo.model.Folder
import com.example.todo.service.FolderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
class FolderController (val folderService: FolderService) {
    private val logger: Logger = LoggerFactory.getLogger(FolderController::class.java)
    @PostMapping("/createFolder")
    fun createFolder(@RequestBody name: String): Folder?{
        val newFolder : Folder? = folderService.createFolder(name)
        if (newFolder == null){
            logger.warn("Folder Not Created!")
        }
        return newFolder
    }

    @GetMapping("/readFolder/{id}")
    fun readFolder(@PathVariable id: String): Folder?{
        return folderService.readFolder(id)
    }

    @PutMapping("/updateFolder/{id}")
    fun updateFolder(@PathVariable id: String, @RequestBody name: String): Folder?{
        val newFolder : Folder? = folderService.updateFolder(id, name)
        if (newFolder == null){
            logger.warn("Folder Not Updated!")
        }
        return newFolder
    }

    @DeleteMapping("/deleteFolder/{id}")
    fun deleteFolder(@PathVariable id: String): Boolean{
        val newFolder : Boolean = folderService.deleteFolder(id)
        if (!newFolder){
            logger.warn("Folder Not Deleted!")
        }
        return newFolder
    }
}