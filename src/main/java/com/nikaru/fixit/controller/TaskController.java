package com.nikaru.fixit.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikaru.fixit.domain.CreateTaskRequest;
import com.nikaru.fixit.domain.UpdateTaskRequest;
import com.nikaru.fixit.domain.dto.CreateTaskRequestDto;
import com.nikaru.fixit.domain.dto.TaskResponseDto;
import com.nikaru.fixit.domain.dto.UpdateTaskRequestDto;
import com.nikaru.fixit.domain.dto.UserSummaryDto;
import com.nikaru.fixit.domain.entity.Task;
import com.nikaru.fixit.domain.entity.User;
import com.nikaru.fixit.service.TaskService;
import com.nikaru.fixit.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody CreateTaskRequestDto createTaskRequestDto,
            @AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        User owner = userService.getOrCreateUser(email);
        CreateTaskRequest createTaskRequest = fromRequest(createTaskRequestDto, owner);
        Task task = taskService.createTask(createTaskRequest);
        TaskResponseDto createdTaskDto = toResponse(task);
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> listTasks() {
        List<Task> tasks = taskService.listTasks();
        List<TaskResponseDto> taskDtos = tasks.stream().map(this::toResponse).toList();
        return ResponseEntity.ok(taskDtos);
    }

    @PutMapping("{taskId}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable("taskId") UUID taskId,
            @Valid @RequestBody UpdateTaskRequestDto updateTaskRequestDto,
            @AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        User completer = userService.getOrCreateUser(email);
        UpdateTaskRequest updateTaskRequest = fromRequest(updateTaskRequestDto,completer);
        Task task = taskService.updateTask(taskId, updateTaskRequest);
        TaskResponseDto updatedTaskDto = toResponse(task);
        return ResponseEntity.ok(updatedTaskDto);
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId, @AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        User requester = userService.getOrCreateUser(email);
        taskService.deleteTask(taskId, requester);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private TaskResponseDto toResponse(Task task) {
        return new TaskResponseDto(task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(),
                task.getPriority(), task.getStatus(), toUserSummary(task.getOwner()), toUserSummary(task.getCompleter()));
    }

    private CreateTaskRequest fromRequest(CreateTaskRequestDto requestDto, User owner) {
        return new CreateTaskRequest(requestDto.title(), requestDto.description(), requestDto.dueDate(),
                requestDto.priority(), owner);
    }

    private UpdateTaskRequest fromRequest(UpdateTaskRequestDto requestDto, User completer) {
        return new UpdateTaskRequest(requestDto.title(), requestDto.description(), requestDto.dueDate(),
                requestDto.status(), requestDto.priority(), completer);
    }

    private UserSummaryDto toUserSummary(User user) {
        if (user == null) {
            return null;
        }
        return new UserSummaryDto(user.getId(), user.getEmail());
    }

}
