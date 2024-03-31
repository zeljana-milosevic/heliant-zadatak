package com.heliant.exceptions;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<ApiErrorResponse>> handleNoHandlerFoundException(
    		MethodArgumentNotValidException ex, HttpServletRequest httpServletRequest) {
    	
    	List<ApiErrorResponse> apiErrorResponses = new ArrayList<ApiErrorResponse>();
    	
    	for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
    		
    		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
    		apiErrorResponse.setCode(500);
    	    apiErrorResponse.setOpis("Nije validan unos.");
    	    apiErrorResponse.setEntitet(objectError.getObjectName());
			apiErrorResponse.setSvojstvoEntiteta(((FieldError) objectError).getField());
			apiErrorResponse.setNarusenaValidacija(objectError.getDefaultMessage());
			
			apiErrorResponses.add(apiErrorResponse);
			
		}
    	
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponses);
    }    
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<List<ApiErrorResponse>> handleSQLIntegrityConstraintViolationException(
    		SQLIntegrityConstraintViolationException ex, HttpServletRequest httpServletRequest) {
    	
    	List<ApiErrorResponse> apiErrorResponses = new ArrayList<ApiErrorResponse>();
    	
    	do {
    		
    		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
    		apiErrorResponse.setCode(500);
    	    apiErrorResponse.setOpis("Nije validan unos.");
			apiErrorResponse.setNarusenaValidacija(ex.getMessage());
			
			apiErrorResponses.add(apiErrorResponse);
			
		} 
    	
    	while (ex.getNextException() != null);
    	
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponses);
    }

}
