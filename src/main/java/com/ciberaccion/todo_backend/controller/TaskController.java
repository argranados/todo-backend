package com.ciberaccion.todo_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import com.ciberaccion.todo_backend.model.Task;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    // Obtener todas las tareas
    @GetMapping
    public List<Task> getTasks() {
        // TODO: Implement get tasks logic
        
        return Arrays.asList(
            new Task(1L, "Sample Task", "This is a sample get task", false, null, null)
        );
    }

    // Obtener una tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        return id == 1L ?
            ResponseEntity.ok(new Task(1L, "Sample Task", "This is a sample task by id", false, null, null)) :
            ResponseEntity.notFound().build();  
    }

    // // Crear una nueva tarea
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        System.out.println("Creating task: " + task.getTitle());
        return new Task(1L, "Sample Created Task", "This is a sample created task", false, null, null);
    }

    // // Actualizar una tarea existente
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        System.out.println("Updating task with id: " + id);
        return id == 1L ?
            ResponseEntity.ok(new Task(1L, "Sample Updated Task", "This is an updated sample task", true, null, null)) :
            ResponseEntity.notFound().build();
    }

    //     // Eliminar una tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        System.out.println("Deleting task with id: " + id);
        return ResponseEntity.noContent().build();
    }
}
