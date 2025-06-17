package com.aits.Safe.Locker.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;




@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException ex)
    {
		ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.CONFLICT.value(),"User already exists",ex.getMessage());;
		return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex)
	{
		ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),"User with this mobile number or id does not exist",ex.getMessage());;
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex)
	{
		ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.UNAUTHORIZED.value(),"Password is incorrect",ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(VehicleNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleVehicleNotFoundException(VehicleNotFoundException ex)
	{
		ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),"Vehicle Not Found",ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ServiceRecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleServiceRecordNotFoundException(ServiceRecordNotFoundException ex)
	{
		ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),"ServiceRecord Not Found",ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(WorkItemNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleWorkItemNotFoundException(WorkItemNotFoundException ex)
	{
		ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),"WorkItem Not Found",ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);	
	}

	@ExceptionHandler(NullValueException.class)
	public ResponseEntity<ErrorResponse> handleNullValueException(NullValueException ex)
	{
		ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.NO_CONTENT.value(),"Need value is null",ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.NO_CONTENT);
	}
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex)
	{
		ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.CONFLICT.value(),"Customer already exists",ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
	}
}
