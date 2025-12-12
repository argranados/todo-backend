package com.ciberaccion.todo_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.ciberaccion.todo_backend.model.Task;
import com.ciberaccion.todo_backend.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Obtener todas las tareas
    @GetMapping
    public List<Task> getTasks() {
        // TODO: Implement get tasks logic
        System.out.println("Fetching all tasks");
        return (List<Task>) taskService.getAllTasks();

    }

    // Obtener una tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        System.out.println("Fetching task with id: " + id);
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
        System.out.println("Creating task: " + task.getTitle());

        return ResponseEntity.ok(taskService.createTask(task));
    }

    // // Actualizar una tarea existente
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        System.out.println("Updating task with id: " + id);
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
        System.out.println("Deleting task with id: " + id);
        return ResponseEntity.noContent().build();
    }
}
