package com.nikaru.fixit.domain;

import java.time.LocalDate;

import com.nikaru.fixit.domain.entities.TaskPriority;
import com.nikaru.fixit.domain.entities.TaskStatus;

public record UpdateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskStatus status,
    TaskPriority priority
) {

}
