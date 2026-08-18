package com.nikaru.fixit.domain.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.nikaru.fixit.domain.entities.TaskPriority;
import com.nikaru.fixit.domain.entities.TaskStatus;

public record TaskDto(
    UUID id,
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority,
    TaskStatus status
) {

}
