package com.ciberaccion.todo_backend.service;

import com.ciberaccion.todo_backend.model.Task;
import com.ciberaccion.todo_backend.repository.TaskRepository;

public class TaskService {

    TaskRepository taskRepository;
    
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(Task task) {
        Task existingTask = getTaskById(task.getId());
        existingTask.setTitle(task.getTitle());
        existingTask.setCompleted(task.getCompleted());
        existingTask.setDescription(task.getDescription());
                
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

}
