package com.jsp.jspwfm.Exception;

public class UserNotFoundException extends Exception{

	String message="user not found ";
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserNotFoundException()
	{
		
	}
	public UserNotFoundException(String msg)
	{
		this.message=msg;
	}
}
