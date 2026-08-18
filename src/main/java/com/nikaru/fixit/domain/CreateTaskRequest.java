package com.nikaru.fixit.domain;

import java.time.LocalDate;

import com.nikaru.fixit.domain.entities.TaskPriority;

public record CreateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority
) {

}
