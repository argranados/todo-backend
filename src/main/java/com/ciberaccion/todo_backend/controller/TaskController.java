package com.ciberaccion.todo_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.ciberaccion.todo_backend.model.Task;
import com.ciberaccion.todo_backend.service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {    

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Obtener todas las tareas
    @GetMapping
    public List<Task> getTasks() {
        logger.info("Se ejecutó el endpoint /api/tasks [GET]");
        
        return (List<Task>) taskService.getAllTasks();

    }

    // Obtener una tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        logger.info("Se ejecutó el endpoint /api/tasks/{} [GET]", id);

        try {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }

    // // Crear una nueva tarea
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        logger.info("Se ejecutó el endpoint /api/tasks [POST]");

        return ResponseEntity.ok(taskService.createTask(task));

    }

    // // Actualizar una tarea existente
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        logger.info("Se ejecutó el endpoint /api/tasks/{} [PUT]", id);

        try {
            updatedTask.setId(id);
            Task task = taskService.updateTask(updatedTask);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //     // Eliminar una tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        logger.info("Se ejecutó el endpoint /api/tasks/{} [DELETE]", id);
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
