package com.arcade.alibou.exception;

import com.arcade.alibou.controller.StudentController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice(assignableTypes = StudentController.class)
public class StudentControllerExceptions extends RuntimeException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        var error = new HashMap<String /*Filed*/, String /*message*/>();
        exp.getBindingResult()
                .getAllErrors()
                .forEach(errors -> {
                    var fieldName = ((FieldError) errors).getField();
                    var errorMessage = errors.getDefaultMessage();
                    error.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
