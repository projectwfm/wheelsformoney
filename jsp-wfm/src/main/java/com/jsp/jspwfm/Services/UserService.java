package com.jsp.jspwfm.Services;

import com.jsp.jspwfm.Dao.OtpRepository;
import com.jsp.jspwfm.Dao.UsersRepository;
import com.jsp.jspwfm.Exception.PasswordInvalidException;
import com.jsp.jspwfm.Exception.UserAlreadyExistsException;
import com.jsp.jspwfm.Exception.UserNotFoundException;
import com.jsp.jspwfm.Models.Entities.MailMessage;
import com.jsp.jspwfm.Models.Entities.Otp;
import com.jsp.jspwfm.Models.Entities.User;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;




@Service
@Component
public class UserService {

    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
   	JavaMailSender mailsender;
    @Value("${spring.mail.username}") 
    private String fromemail;

  
    public User getUser(String username, String password)
    {
        return usersRepository.getUserByUsername(username);
    }
    public boolean handleSignUp(User user)
    {
    	User reslut=usersRepository.getUserByUsername(user.getUsername());
		try
		{
			if(reslut!=null)
			{
				throw new UserAlreadyExistsException();
				
			}
			else
			{
				usersRepository.save(user);
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
    }
	public Object login(String usernameOrEmail, String password) {

		User user = usersRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

		if (user != null) {
			if (user.getPassword().equals(password))

			{
				return user;
			} 
			else {
				try {
					throw new PasswordInvalidException();
				} catch (PasswordInvalidException exception) {
					return exception.getMessage();

				}
			}
		}

		try

		{
			throw new UserNotFoundException();
		} catch (UserNotFoundException exception) {
			return exception.getMessage();

		}
	}

	 public Object pswdrequest(String email)
	    {
	    	 User u1=usersRepository.getUserBydata(email);
	    	if(u1!=null)
	    	{
	    		Random random= new Random();
	    		int OTP=random.nextInt(100000, 999999);
	    		System.out.println("Genereated otp random  "+OTP);
	    		
	    		SimpleMailMessage mailMessage = new SimpleMailMessage();
	    		
	    		 mailMessage.setFrom("asrgshree@gmailcom");
	             mailMessage.setTo(u1.getEmail());
	             mailMessage.setText("this is your OTP "+OTP);
	             mailMessage.setSubject("OTP from WFM JSPider");
	             
	             javaMailSender.send(mailMessage);	            
	    		return OTP;
	    	}
	    	else
	    	{
	    		return "No Account found with Email "+email;
	    	}
	    
	    }

	 public int pswdrequesttype(String email, String type)
	    {
	    	User u1=usersRepository.getUserBydata(email);
	    	
	    	if(type.equals("signup"))
	    	{
	    		if(u1==null)
		    	{
		    		try
		    		{
		    			Random random= new Random();
			    		int OTP=random.nextInt(100000, 999999);
			    		System.out.println("Genereated otp random  "+OTP);
			    		
			    		SimpleMailMessage mailMessage = new SimpleMailMessage();
			    		
			    		 mailMessage.setFrom("wfmbeta@gmail.com");
			             mailMessage.setTo(u1.getEmail());
			             mailMessage.setText("this is your SignUp OTP "+OTP);
			             mailMessage.setSubject("OTP from WFM SignUp");
			             
			             javaMailSender.send(mailMessage);	             
			    		return OTP;
		    		}
		    		catch(Exception e)
		    		{
		    			e.printStackTrace();
		    			return 0;
		    		}
		    	}
		    	else
		    	{
		    		return 0;
		    	}
	    	}
	    	else
	    	{
	    		if(u1!=null)
		    	{
		    		try
		    		{
		    			Random random= new Random();
			    		int OTP=random.nextInt(100000, 999999);
			    		System.out.println("Genereated otp random  "+OTP);
			    		
			    		SimpleMailMessage mailMessage = new SimpleMailMessage();
			    		
			    		 mailMessage.setFrom("wfmbeta@gmail.com");
			             mailMessage.setTo(u1.getEmail());
			             mailMessage.setText("this is your Forget password OTP "+OTP);
			             if(type.equals("forgetpassword"))
			             {
			            	 mailMessage.setSubject("Reset Password");
			             }
			             else
			             {
			            	 mailMessage.setSubject("Login OTP");
			             }
			             
			             
			             javaMailSender.send(mailMessage);	            
			    		return OTP;
		    		}
		    		catch(Exception e)
		    		{
		    			e.printStackTrace();
		    			return 0;
		    		}
		    	}
		    	else
		    	{
		    		return 0;
		    	}
	    	}
	    
	    }
	  
	public String resetpassword(String newpassword, String email)
	{
		User u1=usersRepository.getUserBydata(email);
		u1.setPassword(newpassword);
		usersRepository.save(u1);
		return "Password updated succefully....!!!!!!";
	}
	
	@Autowired
	OtpRepository Otprepository;

	public String verifymail(String email, String type) {
		User  u1 = null;
		Otp o=new Otp();
     
		if (type.equals("signup")) {
			u1 = usersRepository.getUserBydata(email);

			if (u1 == null) {
				Random random = new Random();
				int OTP = random.nextInt(100000, 999999);
				MailMessage mm=new MailMessage();
	    		String str=mm.setotpTemplate(OTP,type);
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setFrom(fromemail);
				mailMessage.setTo(email);
				mailMessage.setText(str);
				mailMessage.setSubject("signup OTP");
				mailsender.send(mailMessage);
				o.setEmail(email);
				o.setOtp(OTP);
				Otprepository.save(o);
				String otp=Integer.toString(OTP);
				return otp;
			} else {
				try {
					throw new UserAlreadyExistsException();
				} catch (UserAlreadyExistsException exception) {
					return exception.getMessage();

				}

			}
		} else {
			u1 = usersRepository.getUserBydata(email);

			if (u1 != null) {
				Random random = new Random();
				int OTP = random.nextInt(100000, 999999);
				MailMessage mm=new MailMessage();
	    		String str=mm.setotpTemplate(OTP,type);
	    		o.setEmail(email);
				o.setOtp(OTP);
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				o.setEmail(email);
				o.setOtp(OTP);
				Otprepository.save(o);
				mailMessage.setFrom(fromemail);
				mailMessage.setTo(u1.getEmail());
				if (type.equals("forget")) {
					mailMessage.setText(str);
					mailMessage.setSubject("Reset password");
				} else {
					mailMessage.setText(str);
					mailMessage.setSubject("Login OTP");
				}

				mailsender.send(mailMessage);
				String otp=Integer.toString(OTP);
				return otp;
			} else {
				try {
					throw new UserNotFoundException();
				} catch (UserNotFoundException exception) {
					return exception.getMessage();

				}

			}
		}

	}

	public boolean verifyOTP(String email,int otp) {
		
		Otp o=Otprepository.findByEmail(email);
  
		if (otp == o.getOtp()) {
			
			Otprepository.delete(o);
			return true;
		} else 
		{
			return false;
		}

	}

	
}
