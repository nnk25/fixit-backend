package com.nikaru.fixit.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nikaru.fixit.domain.dto.ErrorDto;
import com.nikaru.fixit.exception.TaskNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException e) {
        String error = e.getBindingResult().getFieldErrors().stream()
            .findFirst()
            .map(x -> x.getDefaultMessage())
            .orElse("Validation Failed");
        ErrorDto errorDto = new ErrorDto(error);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTaskNotFoundExceptions(TaskNotFoundException ex) {
    UUID taskUUID = ex.getId();
    ErrorDto errorDto = new ErrorDto(String.format("Task with ID '%s' not found", taskUUID));
    return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
