package com.learning.hello.exception;

public class ReadingSizeMismatchException extends ReadingException{
	
	private static final long serialVersionUID = 1L;

	public ReadingSizeMismatchException(String message) {
		super(message);
	}

}