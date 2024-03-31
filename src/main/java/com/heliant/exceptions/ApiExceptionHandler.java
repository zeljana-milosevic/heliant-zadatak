package com.heliant.exceptions;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.TransientPropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.heliant.model.ApiErrorResponse;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<ApiErrorResponse>> handleNoHandlerFoundException (
    		MethodArgumentNotValidException ex, HttpServletRequest httpServletRequest) {
    	
    	List<ApiErrorResponse> apiErrorResponses = new ArrayList<ApiErrorResponse>();
    	
    	for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
    		
    		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
    		apiErrorResponse.setCode(404);
    	    apiErrorResponse.setOpis("Nije validan unos podataka za svojstvo: " + ((FieldError) objectError).getField() +
    	    		" od entiteta: " + objectError.getObjectName());
			apiErrorResponse.setOriginalnaPoruka(objectError.getDefaultMessage());
			
			apiErrorResponses.add(apiErrorResponse);
			
		}
    	
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponses);
    }    
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<List<ApiErrorResponse>> handleSQLIntegrityConstraintViolationException (
    		SQLIntegrityConstraintViolationException ex, HttpServletRequest httpServletRequest) {
    	
    	List<ApiErrorResponse> apiErrorResponses = new ArrayList<ApiErrorResponse>();
    	
    	do {
    		
    		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
    		apiErrorResponse.setCode(404);
    	    apiErrorResponse.setOpis("Nije moguce izvrsiti datu operaciju jer je naruseno odredjeno SQL ogranicenje.");
			apiErrorResponse.setOriginalnaPoruka(ex.getMessage());
			
			apiErrorResponses.add(apiErrorResponse);
			
		} 
    	
    	while (ex.getNextException() != null);
    	
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponses);
    }
    
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentTypeMismatchException (
    		MethodArgumentTypeMismatchException ex, HttpServletRequest httpServletRequest) {
    	
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setCode(404);
	    apiErrorResponse.setOpis("Neodgovarajuci tip podatka je prosledjen kao parametar. Zehtevani tip podatka je: " + ex.getRequiredType());
		apiErrorResponse.setOriginalnaPoruka(ex.getMessage());
		
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponse);
    }
    
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException (
    		BadCredentialsException ex, HttpServletRequest httpServletRequest) {
    	
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setCode(404);
	    apiErrorResponse.setOpis("Ne postoji korisnik sa prosledjenim korisnickim imenom i lozinkom.");
		apiErrorResponse.setOriginalnaPoruka(ex.getMessage());
		
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponse);
    }
    
    @ExceptionHandler({TransientPropertyValueException.class})
    public ResponseEntity<ApiErrorResponse> handleTransientPropertyValueException (
    		TransientPropertyValueException ex, HttpServletRequest httpServletRequest) {
    	
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setCode(404);
	    apiErrorResponse.setOpis("Moze se samo referencirati na entite koji su vec perzistentni u bazi podataka.");
		apiErrorResponse.setOriginalnaPoruka(ex.getMessage());
		
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponse);
    }
    
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException (
    		IllegalArgumentException ex, HttpServletRequest httpServletRequest) {
    	
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setCode(404);
	    apiErrorResponse.setOpis("Prilikom izmene entiteta mora se proslediti neka vrednost za id.");
		apiErrorResponse.setOriginalnaPoruka(ex.getMessage());
		
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponse);
    }
    
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException (
    		DataIntegrityViolationException ex, HttpServletRequest httpServletRequest) {
    	
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setCode(404);
	    apiErrorResponse.setOpis("Naruseno je neko ogranicenje integriteta u bazi podataka.");
		apiErrorResponse.setOriginalnaPoruka(ex.getMessage());
		
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponse);
    }

}
