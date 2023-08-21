package com.learning.hello.exception;

public class NonAscendingReadingException extends ReadingException{
	
	private static final long serialVersionUID = 1L;

	public NonAscendingReadingException(String message) {
		super(message);
	}
	
}