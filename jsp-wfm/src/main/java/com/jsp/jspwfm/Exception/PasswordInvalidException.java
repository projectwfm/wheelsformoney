package com.jsp.jspwfm.Exception;

public class PasswordInvalidException extends Exception{
	
	String message="password is invalid";
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public PasswordInvalidException()
	{
		
	}
	public PasswordInvalidException(String msg)
	{
		this.message=msg;
	}
	

}
