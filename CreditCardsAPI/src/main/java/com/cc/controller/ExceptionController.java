/**
 * 
 */
package com.cc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cc.exceptions.InvalidCCNumberException;
import com.cc.exceptions.RequestFormatException;
import com.cc.model.ApiError;

/**
 * @author Administrator
 *
 */
@ControllerAdvice
public class ExceptionController {
	private static Logger log = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler(value = InvalidCCNumberException.class)
	public ResponseEntity<ApiError> exception(InvalidCCNumberException exception) {
		log.error("Error occurred :" + exception.getErrorMsg());
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "invalid credit card number", exception.getErrorMsg());
		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = RequestFormatException.class)
	public ResponseEntity<ApiError> exception(RequestFormatException exception) {
		log.error("Error occurred :" + exception.getErrorMsg());
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "invalid input", exception.getErrorMsg());
		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiError> exception(Exception exception) {
		log.error("Error occurred :" + exception.getLocalizedMessage());
		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "invalid input", exception.getLocalizedMessage());
		return new ResponseEntity<ApiError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
