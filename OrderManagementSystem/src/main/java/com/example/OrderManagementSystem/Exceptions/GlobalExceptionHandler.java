package com.example.OrderManagementSystem.Exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleNotFound(ResourceNotFoundException e){
		return buildResponce("Not Found ",e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRquest(ResourceNotFoundException e){
		return buildResponce("Bad Request ",e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException e ){
		return buildResponce("Validation Failed",e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	private ResponseEntity<?> buildResponce(String error, String message, HttpStatus status){
		Map<String, Object> body = new HashMap<>();
		body.put("error", error);
		body.put("message", message);
		body.put("status", status.value());
		return new ResponseEntity<>(body,status);
	}
	
}