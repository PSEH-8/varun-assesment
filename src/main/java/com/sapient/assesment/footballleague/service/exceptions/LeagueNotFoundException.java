package com.sapient.assesment.footballleague.service.exceptions;

/**
 *  Specific Exception to be thrown when country entered is not found 
 * 
 */
public class LeagueNotFoundException extends RuntimeException{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -457898556774192255L;

	/**
	 * 
	 */

	public LeagueNotFoundException(String message) {
	        super(message);
	    }
}
