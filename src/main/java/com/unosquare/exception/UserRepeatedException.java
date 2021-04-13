package com.unosquare.exception;

public class UserRepeatedException extends Exception { 

	private static final long serialVersionUID = -2363199477058223436L;

	public UserRepeatedException(String errorMessage) {
        super(errorMessage);
    }
}