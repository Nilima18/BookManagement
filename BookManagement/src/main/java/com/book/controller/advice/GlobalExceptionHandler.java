package com.book.controller.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.book.exception.BookNotFoundException;
import com.book.exception.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value =BookNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleEx(BookNotFoundException e){
		return new ResponseEntity<ErrorMessage>(
				new ErrorMessage(
						e.getMessage(),
						LocalDateTime.now(),
						e.getClass().toString())
				, HttpStatus.NOT_FOUND);
				
	}

}
