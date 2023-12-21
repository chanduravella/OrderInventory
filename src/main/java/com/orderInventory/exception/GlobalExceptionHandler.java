package com.orderInventory.exception;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.orderInventory.entity.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
@ExceptionHandler(ResourceNotFoundException.class)
	
	public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException exception) {
		
		ErrorResponse error = new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value()); 
		error.setMessage(exception.getMessage()); 
		error.setTimeStamp(LocalDateTime.now()); 
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
	}

@ExceptionHandler(InvalidCredentialsException.class)

public ResponseEntity<ErrorResponse> handleException(InvalidCredentialsException exception) {
	
	ErrorResponse error = new ErrorResponse();
	
	error.setStatus(HttpStatus.NOT_FOUND.value()); 
	error.setMessage(exception.getMessage()); 
	error.setTimeStamp(LocalDateTime.now()); 
	
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
}

}
