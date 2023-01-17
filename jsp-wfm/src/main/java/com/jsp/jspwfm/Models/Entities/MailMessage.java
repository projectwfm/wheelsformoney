package com.jsp.jspwfm.Models.Entities;



public class MailMessage{
	
	public final static String s1="You just attempted to login to wfm. Your OTP is {OTP}.\r\n"
			+ "Please reach out to customer support if you did not attempt a login.\r\n DONT SHARE THE OTP WITH ANYONE."
			+ "\r\n"
			+ "\r\n"
			+"Thanks & Regards \r\n"
			+ "Team WFM.";
	public final static String s2="You just attempted to signup to wfm. Your OTP is {OTP}.\r\n"
			+ "Please reach out to customer support if you did not attempt a signup.\r\n DONT SHARE THE OTP WITH ANYONE."
			+ "\r\n"
			+"\r\n"
			+"Thanks & Regards \r\n"
			+ "Team WFM.";
	public final static String s3="You just attempted to change the password of wfm. Your OTP is {OTP}.\r\n"
			+ "Please reach out to customer support if you did not attempt a reset password.\r\n DONT SHARE THE OTP WITH ANYONE."
			+ " \r\n"
			+ "\r\n"
			+"Thanks & Regards \r\n"
			+ "Team WFM.";
	
	  public  String setotpTemplate(int otp,String type)
	    {
		  if(type.equals("signup"))
		  {
			  String rep = s2.replace("{OTP}",""+otp );
		    	
		    	return rep; 
		  }
		  else if(type.equals("login"))
		  {
			  String rep = s1.replace("{OTP}",""+otp );
		    	
		    	return rep; 
		  }
		  else
		  {
			  String rep = s3.replace("{OTP}",""+otp );
		    	
		    	return rep;
		  }
	    	
	    }
	

		
	

}
