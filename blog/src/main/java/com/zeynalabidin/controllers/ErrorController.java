package com.zeynalabidin.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.zeynalabidin.domain.dtos.ApiErrorResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
		log.error("Caught exception", ex);
		ApiErrorResponse error = ApiErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message("An unexcepted error occured").build();
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiErrorResponse> handleIllegalArgumentExcepiton(IllegalArgumentException ex) {

		ApiErrorResponse error = ApiErrorResponse.builder().status(HttpStatus.BAD_REQUEST.value())
				.message(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiErrorResponse> handleIllegalStateExcepiton(IllegalStateException ex) {

		ApiErrorResponse error = ApiErrorResponse.builder().status(HttpStatus.CONFLICT.value()).message(ex.getMessage())
				.build();
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
		ApiErrorResponse error = ApiErrorResponse.builder().status(HttpStatus.UNAUTHORIZED.value())
				.message("Incorrect username or password").build();
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
		ApiErrorResponse error = ApiErrorResponse.builder().status(HttpStatus.NOT_FOUND.value())
				.message(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}


}
