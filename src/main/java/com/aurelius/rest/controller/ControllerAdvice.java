package com.aurelius.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aurelius.rest.exception.ConflictException;
import com.aurelius.rest.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleNotFound() {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<Object> handleConflict() {
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler({RuntimeException.class, Exception.class})
	public ResponseEntity<Object> handleInternalServerError() {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
