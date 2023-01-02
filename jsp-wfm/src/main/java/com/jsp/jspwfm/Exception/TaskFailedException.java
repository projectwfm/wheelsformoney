package com.jsp.jspwfm.Exception;

public class TaskFailedException extends Exception{
	String message="task failed";
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TaskFailedException()
	{
		
	}
	public TaskFailedException(String msg)
	{
		this.message=msg;
	}

}
