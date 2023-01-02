package com.jsp.jspwfm.Exception;

public class UserAlreadyExistsException extends Exception{
	String message="user already exists";
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserAlreadyExistsException()
	{
		
	}
	public UserAlreadyExistsException(String msg)
	{
		this.message=msg;
	}

}
