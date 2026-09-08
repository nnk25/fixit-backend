package com.nikaru.fixit.exception;

public class TaskAccessDeniedException extends RuntimeException {

    public TaskAccessDeniedException(String message) {
        super(message);
    }
}
