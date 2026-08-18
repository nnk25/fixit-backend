package com.nikaru.fixit.mapper.impl;

import org.springframework.stereotype.Component;

import com.nikaru.fixit.domain.CreateTaskRequest;
import com.nikaru.fixit.domain.UpdateTaskRequest;
import com.nikaru.fixit.domain.dto.CreateTaskRequestDto;
import com.nikaru.fixit.domain.dto.TaskDto;
import com.nikaru.fixit.domain.dto.UpdateTaskRequestDto;
import com.nikaru.fixit.domain.entities.Task;
import com.nikaru.fixit.mapper.TaskMapper;

@Component
public class TaskMapperImpl implements TaskMapper{

    @Override
    public CreateTaskRequest fromDto(CreateTaskRequestDto dto) {
        return new CreateTaskRequest(
            dto.title(),
            dto.description(),
            dto.dueDate(),
            dto.priority()
        );
    }

    @Override
    public UpdateTaskRequest fromDto(UpdateTaskRequestDto dto) {
        return new UpdateTaskRequest(
            dto.title(),
            dto.description(),
            dto.dueDate(),
            dto.status(),
            dto.priority()
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            task.getPriority(),
            task.getStatus()
        );
    }


}
