package com.ciberaccion.todo_backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ciberaccion.todo_backend.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Buscar todas las tareas de un usuario específico
    List<Task> findByUserId(Long userId);

    // Buscar tareas por estado (completadas o no)
    List<Task> findByCompleted(Boolean completed);

    // Buscar tareas con fecha de vencimiento antes de cierta fecha
    // List<Task> findByDueDateBefore(LocalDateTime date);

    // Buscar tareas con fecha de vencimiento después de cierta fecha
    // List<Task> findByDueDateAfter(LocalDateTime date);


}
