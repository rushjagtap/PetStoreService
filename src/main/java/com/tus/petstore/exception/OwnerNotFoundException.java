package com.tus.petstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND) 
public class OwnerNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public OwnerNotFoundException(String message)
	{
		super(message);
	}

}
