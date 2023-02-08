package com.jsp.jspwfm.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jsp.jspwfm.Dao.BikeDao;
import com.jsp.jspwfm.Dao.CarDao;
import com.jsp.jspwfm.Dao.OtpRepository;
import com.jsp.jspwfm.Dao.UsersRepository;
import com.jsp.jspwfm.Exception.PasswordInvalidException;
import com.jsp.jspwfm.Exception.UserAlreadyExistsException;
import com.jsp.jspwfm.Exception.UserNotFoundException;
import com.jsp.jspwfm.Models.Entities.Address;
import com.jsp.jspwfm.Models.Entities.Address1;
import com.jsp.jspwfm.Models.Entities.MailMessage;
import com.jsp.jspwfm.Models.Entities.Otp;
import com.jsp.jspwfm.Models.Entities.User;




@Service
@Component
public class UserService {
	
	static Logger log= LogManager.getLogger(UserService.class);

    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
   	JavaMailSender mailsender;
    @Value("${spring.mail.username}") 
    private String fromemail;

    
    @Autowired
    BikeDao bikedao;
    
    @Autowired
    CarDao cardao;
    
    public User getUser(String username, String password)
    {
        return usersRepository.getUserByUsername(username);
    }
    public boolean handleSignUp(User user)
    {
    	log.info("handle signUp excuted for email ");
    	User reslut=usersRepository.getUserByUsername(user.getUsername());
		try
		{
			if(reslut!=null)
			{
				log.error("Exception occured ");
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
			log.info(" Exception details:  ");
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
					log.error("Exception occured ");
					throw new PasswordInvalidException();
				} catch (PasswordInvalidException exception) {
					return exception.getMessage();

				}
			}
		}

		try

		{
			log.error("Exception occured ");
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
		    			log.info(" Exception details:  ");
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
		    			log.info(" Exception details:  ");
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
					log.error("Exception occured ");
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
					log.error("Exception occured ");
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
	public boolean edit(User user)
	 {
		 User u = usersRepository.getUserByUsername(user.getUsername());
		if(u!=null) { 
		 Address ad = user.getAddress();
		 Address1 ad1 = ad.getAddress1(); 
		 ad.setAddress1(ad1);
		 u.setAddress(ad);
		 u.setUsername(user.getUsername());
		 u.setDob(user.getDob());
		 u.setGender(user.getGender());
		 u.setPhno(user.getPhno());
		 usersRepository.save(u);
		return true;
		}
		return false;
	 }
	 public User getUserData(String username)
	 {
		 return usersRepository.getUserByUsername(username);
	 }
	 
	 public Map getVehicle()
		{
			
			Map<String,List> m1=new HashMap<String,List>();
			m1.put("bike", bikedao.findAll());
			m1.put("car",cardao.findAll());
			return m1;
		}
	 
	public boolean cardpayment(long cardno,int cvv,String email)
		 {
			 int len = (int) (Math.log10(cardno) + 1);
			 int len1 = (int) (Math.log10(cvv) + 1);
			 if(len==16&&len1==3)
			 {
		    		String s=MailMessage.s4;
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setFrom(fromemail);
					mailMessage.setTo(email);
					mailMessage.setText(s);
					mailMessage.setSubject("PAYMENT MAIL");
					mailsender.send(mailMessage);
				 return true;
			 }
			 else
			 {
				 return false;
			 }
		 }
		 public boolean upipayment(String upi_id,String email )
		 {
			 int i=upi_id.indexOf('@');
			 if(i>0)
			 {
				 
				 String s=MailMessage.s5;
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setFrom(fromemail);
					mailMessage.setTo(email);
					mailMessage.setText(s);
					mailMessage.setSubject("PAYMENT MAIL");
					mailsender.send(mailMessage);
				 return true;
			 }
			 else
			 {
				 return false;
			 }
		}
 
	
	
}
