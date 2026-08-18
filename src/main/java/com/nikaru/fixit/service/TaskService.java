package com.nikaru.fixit.service;

import java.util.List;
import java.util.UUID;

import com.nikaru.fixit.domain.CreateTaskRequest;
import com.nikaru.fixit.domain.UpdateTaskRequest;
import com.nikaru.fixit.domain.entities.Task;

public interface TaskService {
    Task createTask(CreateTaskRequest request);
    Task updateTask(UUID taskId, UpdateTaskRequest request);
    void deleteTask(UUID taskId);
    List<Task> listTasks();
}
