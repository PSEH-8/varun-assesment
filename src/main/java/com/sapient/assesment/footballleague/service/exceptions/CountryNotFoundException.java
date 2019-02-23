package com.sapient.assesment.footballleague.service.exceptions;

/**
 *  Specific Exception to be thrown when country entered is not found 
 * 
 */
public class CountryNotFoundException extends RuntimeException{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -6684160703468400377L;

	public CountryNotFoundException(String message) {
	        super(message);
	    }
}
