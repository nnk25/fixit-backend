package com.nikaru.fixit.domain.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.nikaru.fixit.domain.entity.TaskPriority;
import com.nikaru.fixit.domain.entity.TaskStatus;

public record TaskResponseDto(
    UUID id,
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority,
    TaskStatus status,
    UserSummaryDto owner,
    UserSummaryDto completer
) {

}
