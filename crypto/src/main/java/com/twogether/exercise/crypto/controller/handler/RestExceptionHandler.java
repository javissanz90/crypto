package com.twogether.exercise.crypto.controller.handler;

import com.twogether.exercise.crypto.exception.ErrorView;
import com.twogether.exercise.crypto.exception.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    /**
     * Handler which catch InvalidDataExceptions and returns a JSON which the detailed information
     * that the API wants to share with the client.
     *
     * @param ex
     * @return JSON {"error": "descriptive error message"}
     */
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Object> invalidDataExceptionHandler(InvalidDataException ex) {
        return new ResponseEntity<>(new ErrorView(ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler which catch Validate exceptions and returns a JSON which the detailed information
     * that the API wants to share with the client.
     *
     * @param ex
     * @return JSON {"field": "descriptive error message"}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>((errors),
                HttpStatus.BAD_REQUEST);
    }
}
