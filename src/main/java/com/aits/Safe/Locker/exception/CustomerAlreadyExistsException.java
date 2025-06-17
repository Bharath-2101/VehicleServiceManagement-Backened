package com.aits.Safe.Locker.exception;


public class CustomerAlreadyExistsException extends RuntimeException{
	
	public CustomerAlreadyExistsException(String message)
	{
		super(message);
	}

}
