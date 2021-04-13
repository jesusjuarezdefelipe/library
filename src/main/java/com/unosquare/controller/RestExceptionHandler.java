package com.unosquare.controller;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.persistence.EntityNotFoundException;

import com.unosquare.exception.InvalidOperationException;
import com.unosquare.exception.UserRepeatedException;
import com.unosquare.util.ApiError;

@Order(1)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   @Override
   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       String error = "Malformed JSON request";
       return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
   }

   @ExceptionHandler(EntityNotFoundException.class)
   protected ResponseEntity<Object> handleEntityNotFound(
		   EntityNotFoundException ex) {
       ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
       apiError.setMessage(ex.getMessage());
       return buildResponseEntity(apiError);
   }
   
   @ExceptionHandler(InvalidOperationException.class)
   protected ResponseEntity<Object> handleInvalidOperation(
		   InvalidOperationException ex) {
       ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY);
       apiError.setMessage(ex.getMessage());
       return buildResponseEntity(apiError);
   }
   
   @ExceptionHandler(UserRepeatedException.class)
   protected ResponseEntity<Object> handleUserRepeatedException(
		   InvalidOperationException ex) {
       ApiError apiError = new ApiError(HttpStatus.CONFLICT);
       apiError.setMessage(ex.getMessage());
       return buildResponseEntity(apiError);
   }
   
   @ExceptionHandler(Exception.class)
   protected ResponseEntity<Object> handleException(
		   InvalidOperationException ex) {
       ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
       apiError.setMessage(ex.getMessage());
       return buildResponseEntity(apiError);
   }
   
   
   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
       return new ResponseEntity<>(apiError, apiError.getStatus());
   }

}
