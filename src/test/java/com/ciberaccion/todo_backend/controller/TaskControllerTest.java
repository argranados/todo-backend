package com.ciberaccion.todo_backend.controller;

import com.ciberaccion.todo_backend.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
@DisplayName("TaskController Unit Tests")
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Task sampleTask;

    @BeforeEach
    void setUp() {
        sampleTask = new Task();
        sampleTask.setId(1L);
        sampleTask.setTitle("Test Task");
        sampleTask.setDescription("Test Description");
        sampleTask.setCompleted(false);
    }

    @Test
    @DisplayName("GET /api/tasks - Should return list of tasks")
    void testGetTasks() throws Exception {
        mockMvc.perform(get("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Sample Task")))
                .andExpect(jsonPath("$[0].description", is("This is a sample get task")))
                .andExpect(jsonPath("$[0].completed", is(false)));
    }

    @Test
    @DisplayName("GET /api/tasks/{id} - Should return task when found")
    void testGetTaskById_Success() throws Exception {
        mockMvc.perform(get("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Sample Task")))
                .andExpect(jsonPath("$.description", is("This is a sample task by id")))
                .andExpect(jsonPath("$.completed", is(false)));
    }

    @Test
    @DisplayName("GET /api/tasks/{id} - Should return 404 when task not found")
    void testGetTaskById_NotFound() throws Exception {
        mockMvc.perform(get("/api/tasks/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/tasks - Should create new task")
    void testCreateTask() throws Exception {
        Task newTask = new Task();
        newTask.setTitle("New Task");
        newTask.setDescription("New Task Description");
        newTask.setCompleted(false);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Sample Created Task")))
                .andExpect(jsonPath("$.description", is("This is a sample created task")))
                .andExpect(jsonPath("$.completed", is(false)));
    }

    @Test
    @DisplayName("POST /api/tasks - Should handle empty request body")
    void testCreateTask_EmptyBody() throws Exception {
        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @DisplayName("PUT /api/tasks/{id} - Should update existing task")
    void testUpdateTask_Success() throws Exception {
        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");
        updatedTask.setCompleted(true);

        mockMvc.perform(put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTask)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Sample Updated Task")))
                .andExpect(jsonPath("$.description", is("This is an updated sample task")))
                .andExpect(jsonPath("$.completed", is(true)));
    }

    @Test
    @DisplayName("PUT /api/tasks/{id} - Should return 404 when task not found")
    void testUpdateTask_NotFound() throws Exception {
        Task updatedTask = new Task();
        updatedTask.setId(999L);
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");
        updatedTask.setCompleted(true);

        mockMvc.perform(put("/api/tasks/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTask)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/tasks/{id} - Should delete task successfully")
    void testDeleteTask() throws Exception {
        mockMvc.perform(delete("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /api/tasks/{id} - Should return 204 even for non-existent task")
    void testDeleteTask_NonExistent() throws Exception {
        mockMvc.perform(delete("/api/tasks/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /api/tasks - Should return empty array structure")
    void testGetTasks_CheckArrayStructure() throws Exception {
        mockMvc.perform(get("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(java.util.List.class)));
    }

    @Test
    @DisplayName("POST /api/tasks - Should accept task with all fields")
    void testCreateTask_WithAllFields() throws Exception {
        Task fullTask = new Task();
        fullTask.setTitle("Complete Task");
        fullTask.setDescription("Task with all fields");
        fullTask.setCompleted(true);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fullTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    @DisplayName("PUT /api/tasks/{id} - Should handle partial updates")
    void testUpdateTask_PartialUpdate() throws Exception {
        String partialUpdate = "{\"title\":\"Updated Title Only\"}";

        mockMvc.perform(put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(partialUpdate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @DisplayName("GET /api/tasks/{id} - Should handle different ID types")
    void testGetTaskById_DifferentIds() throws Exception {
        // Test with valid ID
        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk());

        // Test with different ID
        mockMvc.perform(get("/api/tasks/2"))
                .andExpect(status().isNotFound());
    }
}
