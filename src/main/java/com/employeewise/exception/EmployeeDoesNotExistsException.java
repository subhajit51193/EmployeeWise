package com.employeewise.exception;

public class EmployeeDoesNotExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeDoesNotExistsException() {
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeDoesNotExistsException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
