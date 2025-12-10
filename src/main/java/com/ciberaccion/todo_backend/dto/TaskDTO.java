package com.ciberaccion.todo_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data                   // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor      // Constructor vacío
@AllArgsConstructor     // Constructor con todos los campos
@Builder                // Patrón Builder para instanciar objetos fácilmente
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    // private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId; // en lugar de exponer el objeto User completo

}