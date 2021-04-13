package com.unosquare.exception;

public class InvalidOperationException extends Exception { 

	private static final long serialVersionUID = -4426478985158746966L;

	public InvalidOperationException(String errorMessage) {
        super(errorMessage);
    }
}