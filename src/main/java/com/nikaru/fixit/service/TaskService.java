package com.nikaru.fixit.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nikaru.fixit.domain.CreateTaskRequest;
import com.nikaru.fixit.domain.UpdateTaskRequest;
import com.nikaru.fixit.domain.entity.Task;
import com.nikaru.fixit.domain.entity.TaskStatus;
import com.nikaru.fixit.exception.TaskNotFoundException;
import com.nikaru.fixit.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(CreateTaskRequest request) {
        Instant now = Instant.now();
        Task task = new Task(
                null,
                request.title(),
                request.description(),
                request.dueDate(),
                TaskStatus.OPEN,
                request.priority(),
                now,
                now,
                request.user()
            );
        return taskRepository.save(task);
    }

    public List<Task> listTasks() {
        return taskRepository.findAll(Sort.by(Direction.ASC, Task::getCreatedAt));
    }

    public Task updateTask(UUID taskId, UpdateTaskRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setStatus(request.status());
        task.setPriority(request.priority());
        task.setUpdatedAt(Instant.now());
        task.setCompleter(request.user());

        return taskRepository.save(task);
    }

    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }

}
