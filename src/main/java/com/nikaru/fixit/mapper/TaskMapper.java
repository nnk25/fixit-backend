package com.nikaru.fixit.mapper;

import com.nikaru.fixit.domain.CreateTaskRequest;
import com.nikaru.fixit.domain.UpdateTaskRequest;
import com.nikaru.fixit.domain.dto.CreateTaskRequestDto;
import com.nikaru.fixit.domain.dto.TaskDto;
import com.nikaru.fixit.domain.dto.UpdateTaskRequestDto;
import com.nikaru.fixit.domain.entities.Task;

public interface TaskMapper {
    CreateTaskRequest fromDto(CreateTaskRequestDto dto);
    UpdateTaskRequest fromDto(UpdateTaskRequestDto dto);
    TaskDto toDto(Task task);
    
    
}
