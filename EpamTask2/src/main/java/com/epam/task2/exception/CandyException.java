package com.epam.task2.exception;

public class CandyException extends Exception {
	private static final long serialVersionUID = 1L;

	public CandyException() {
	}

	public CandyException(String message) {
		super(message);
	}

	public CandyException(String message, Throwable cause) {
		super(message, cause);
	}

	public CandyException(Throwable cause) {
		super(cause);
	}
}
