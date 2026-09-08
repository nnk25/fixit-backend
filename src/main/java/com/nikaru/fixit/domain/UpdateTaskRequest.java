package com.nikaru.fixit.domain;

import java.time.LocalDate;

import com.nikaru.fixit.domain.entity.TaskPriority;
import com.nikaru.fixit.domain.entity.TaskStatus;
import com.nikaru.fixit.domain.entity.User;

public record UpdateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskStatus status,
    TaskPriority priority,
    User user
) {

}
