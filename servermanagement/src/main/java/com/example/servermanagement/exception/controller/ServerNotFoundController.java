package com.example.servermanagement.exception.controller;

import com.example.servermanagement.exception.classes.ServerNotFound;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServerNotFoundController {
    @ExceptionHandler(value = ServerNotFound.class)
    public ResponseEntity<Object> exception (ServerNotFound exception) {
        return new ResponseEntity<>("Server does not exist", HttpStatus.NOT_FOUND);
    }
}