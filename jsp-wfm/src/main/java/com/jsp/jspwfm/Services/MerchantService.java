package com.jsp.jspwfm.Services;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jsp.jspwfm.Dao.MerchantDao;
import com.jsp.jspwfm.Dao.OtpRepository;
import com.jsp.jspwfm.Exception.MerchantAlreadyExistsException;
import com.jsp.jspwfm.Exception.MerchantNotFoundException;
import com.jsp.jspwfm.Exception.PasswordInvalidException;
import com.jsp.jspwfm.Models.Entities.MailMessage;
import com.jsp.jspwfm.Models.Entities.Merchant;
import com.jsp.jspwfm.Models.Entities.Otp;
@Service
@Component
public class MerchantService
{
		@Autowired
		MerchantDao merchantDaolayer;
		@Autowired
		OtpRepository Otprepository;
		@Autowired
		JavaMailSender mailsender;
	    @Value("${spring.mail.username}") 
	    private String fromemail;
		
		public Merchant getMerchantByemail(String email) 
		{
			Merchant m= merchantDaolayer.findByEmail(email);
			return m;
		}

		public boolean handlesignUp(Merchant merchant) throws MerchantAlreadyExistsException 
		{	
			try
			{
				Merchant newmerchant= merchantDaolayer.getByMerchantname(merchant.getMerchantname());
				if(newmerchant!= null)
				{
					throw new MerchantAlreadyExistsException();
				}
				else
				{
					newmerchant= merchantDaolayer.save(merchant);
				}
				return true;
			}
			catch(MerchantAlreadyExistsException e)
			{
				System.out.println(e.getMessage());
				return false;
			}
		}

		public Map<String,String> handlelogin(String merchantnameOremail, String password) throws PasswordInvalidException
		{
			Map<String,String>map=new HashMap<>();
			Merchant m=   merchantDaolayer.getByMerchantnameOrEmail(merchantnameOremail);
			try
			{
				if(m!= null && password.equals(m.getPassword()))
				{
					String mid= m.getMerchant_id()+"";
						map.put("name",m.getMerchantname() );
						map.put("id",mid );
						return map;
				}
				else
				{
					throw new PasswordInvalidException();				
				}
			}
			catch(PasswordInvalidException e)
			{
				System.out.println(e.getMessage());
				return map;
			}
			
			
		}

		public boolean handleresetpassword(String newpassword, String email) 
		{
			Merchant merchant= merchantDaolayer.findByEmail(email);
			merchant.setPassword(newpassword);
			Merchant merchant2= merchantDaolayer.save(merchant);
			if(merchant2!= null)
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		public Object verifymail(String email, String type) 
		{
				Merchant  m = null;
				Otp o=new Otp();
	     
				if (type.equals("signup")) 
				{
					m = merchantDaolayer.findByEmail(email);
					try 
					{
						if (m == null) 
						{
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
						}
						else
						{
							throw new MerchantAlreadyExistsException();
						} 
					}
					catch (MerchantAlreadyExistsException exception)
					{
						return exception.getMessage();	
					}

				}
			
			else
			{
				m = merchantDaolayer.findByEmail(email);
				try 
				{
					if (m != null) 
					{
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
						mailMessage.setTo(m.getEmail());
						if (type.equals("forget")) 
						{
							mailMessage.setText(str);
							mailMessage.setSubject("Reset password");
						} 
						else
						{
							mailMessage.setText(str);
							mailMessage.setSubject("Login OTP");
						}
						mailsender.send(mailMessage);
						String otp=Integer.toString(OTP);
						return otp;
					}
					else
					{
						throw new MerchantNotFoundException();
					}
				}
				catch (MerchantNotFoundException exception)
				{
					return exception.getMessage();
				}
			}
		}


		public Boolean verifyOTP(String email, int otp) {
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
