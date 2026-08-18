package com.nikaru.fixit.domain.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.nikaru.fixit.domain.entities.TaskPriority;
import com.nikaru.fixit.domain.entities.TaskStatus;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskRequestDto(
    @NotBlank(message = ERROR_MESSAGE_TITLE_LENGTH)
    @Length(max=255, message = ERROR_MESSAGE_TITLE_LENGTH)
    String title,

    @Nullable
    @Length(max=1000, message = "Description must be less than 1000 characters")
    String description,


    @Nullable
    @FutureOrPresent(message = "Due date must be in the future")
    LocalDate dueDate,

    @NotNull(message = "Status must be provided")
    TaskStatus status,

    @NotNull(message = "Priority must be provided")
    TaskPriority priority
) {
    private static final String ERROR_MESSAGE_TITLE_LENGTH = "Title must be between 1 and 255 characters";
}
