package com.pbl3.supermarket.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<String> handlingRuntimException(RuntimeException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<String> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        // Check if there are any field errors
        if (exception.getBindingResult() != null && !exception.getBindingResult().getFieldErrors().isEmpty()) {
            // Retrieve the first field error message or you can customize it
            return ResponseEntity.badRequest().body(exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        } else {
            return ResponseEntity.badRequest().body("Invalid argument, but no specific error message available.");
        }
    }
}
