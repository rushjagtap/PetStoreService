package com.tus.petstore.exception;

public class PetException extends Exception {
	private static final long serialVersionUID = 1L;

	protected PetException(final String message) {
		super(message);
	}

}
