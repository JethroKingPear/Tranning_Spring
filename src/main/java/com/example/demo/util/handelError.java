package com.example.demo.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
public class handelError {

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<String> handelBindException(MethodArgumentNotValidException e){

        return ResponseEntity.badRequest().body(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
