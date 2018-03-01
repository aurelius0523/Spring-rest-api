package com.aurelius.rest.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.aurelius.rest.Error;
import com.aurelius.rest.enumeration.InternalCode;
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
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException ex) {
		 BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

        Error error = new Error();
        error.setCode(InternalCode.INVALID_REQUEST.getCode());
        error.setMessage(processFieldErrorMessage(fieldErrors));
        error.setTimestamp(Instant.now().atZone(ZoneId.of("UTC")));
        
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	private String processFieldErrorMessage(List<FieldError> fieldErrors) {
        String errorMessage = "The following field(s) are required: " +
	        fieldErrors.stream()
	        	.map(field -> field.getField())
	        	.collect(Collectors.joining(", "))
	        		.replaceAll(", $", "");
        
        return errorMessage;
    }
}
