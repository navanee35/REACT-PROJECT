package com.navanee.demo.service;

import com.navanee.demo.model.Task;
import com.navanee.demo.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.navanee.demo.model.Event;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<Task> getTasksByName(String taskName) {
        return taskRepository.findByTaskName(taskName);
    }

    public List<Task> searchTasksByName(String keyword) {
        return taskRepository.searchByTaskName(keyword);
    }

    public List<Task> getTasksByEvent(Long eventId) {
        return taskRepository.findTasksByEvent(eventId);
    }

    public List<Task> getAllTasks(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Task> taskPage = taskRepository.findAllTasks(pageable);
        return taskPage.getContent(); 
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id);
        task.setTaskName(updatedTask.getTaskName());
        task.setEvent(updatedTask.getEvent());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

     // Method to update a task by eventId
     public int updateTaskByEvent(Long eventId, String taskName, Event event) {
        return taskRepository.updateTaskByEvent(eventId, taskName, event);
    }

    // Method to delete a task by taskName
    public int deleteTaskByTaskName(String taskName) {
        return taskRepository.deleteTaskByTaskName(taskName);
    }
}
