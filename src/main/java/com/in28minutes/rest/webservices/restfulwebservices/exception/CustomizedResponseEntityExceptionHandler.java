package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
//@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		StackTraceElement stacks[] = ex.getStackTrace();
		Stream<StackTraceElement> s = Arrays.stream(stacks);
		s.forEach(stack -> System.out.println(stack.getClassName()+" - "+stack.getMethodName()+" - "+stack.getLineNumber()));
		//System.err.println("Exception Occurred : "+ex.getStackTrace().toString()+""+ex.);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {
		System.out.println("112333333333333+UserNotFoundException-");
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	//handleException is the main method that indirectly calls 'handleMethodArgumentNotValid' in ResponseEntityExceptionHandler
	//handleException is configured to error exception using annotation @ExceptionHandler hence when we try to convert request param to dao object as in case of User in createUser method in UserResource
	//we are using valid annotation that internal pass required data to all methods that handle methodArgument validity just like below
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("112333333333333+handleMEthodArgumentValid exception");
		//in below field is property variable name on validity fails as in User object our name fails if passed as 'as' 
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getBindingResult().getFieldError().getDefaultMessage(), "Method Arguments are not valid.");
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
}
