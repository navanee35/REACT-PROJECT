package com.navanee.demo.controller;

import com.navanee.demo.model.Task;
import com.navanee.demo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create a task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    // Get all tasks (No pagination)
    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    // Get tasks by name
    @GetMapping("/name/{taskName}")
    public ResponseEntity<List<Task>> getTasksByName(@PathVariable String taskName) {
        return ResponseEntity.ok(taskService.getTasksByName(taskName));
    }

    // Search tasks by keyword
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasksByName(@RequestParam String keyword) {
        return ResponseEntity.ok(taskService.searchTasksByName(keyword));
    }

    //  Get tasks by event ID
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Task>> getTasksByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(taskService.getTasksByEvent(eventId));
    }

    // Get all tasks with pagination & sorting
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        List<Task> tasks = taskService.getAllTasks(page, size, sortBy, sortDir);
        return ResponseEntity.ok(tasks);
    }

    // Update task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    //  Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to update a task by event ID
    @PutMapping("/event/{eventId}")
    public ResponseEntity<Integer> updateTaskByEvent(@PathVariable Long eventId, @RequestBody Task task) {
        int result = taskService.updateTaskByEvent(eventId, task.getTaskName(), task.getEvent());
        return ResponseEntity.ok(result);
    }

    // Endpoint to delete a task by taskName
    @DeleteMapping("/name/{taskName}")
    public ResponseEntity<Void> deleteTaskByTaskName(@PathVariable String taskName) {
        taskService.deleteTaskByTaskName(taskName);
        return ResponseEntity.noContent().build();
    }
}
