package com.jsp.jspwfm.Services;


import java.util.HashMap;
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

import com.jsp.jspwfm.Controllers.MerchantController;
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
		static Logger log= LogManager.getLogger(MerchantService.class);
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
			log.info("getting merchant for email "+email);
			Merchant m= merchantDaolayer.findByEmail(email);
			return m;
		}

		public boolean handlesignUp(Merchant merchant) throws MerchantAlreadyExistsException 
		{	
			log.info("handle signUp excuted for email "+merchant.getEmail());
			try
			{
				Merchant newmerchant= merchantDaolayer.getByMerchantname(merchant.getMerchantname());
				if(newmerchant!= null)
				{
					log.error("Exception occured ", new MerchantAlreadyExistsException());
					throw new MerchantAlreadyExistsException();
				}
				else
				{
					log.info("New merchant registration done successfully with email "+merchant.getEmail());
					newmerchant= merchantDaolayer.save(merchant);
				}
				return true;
			}
			catch(MerchantAlreadyExistsException e)
			{
				log.info(" Exception details:  ");
				System.out.println(e.getMessage());
				return false;
			}
		}

		public Map<String,String> handlelogin(String merchantnameOremail, String password) throws PasswordInvalidException
		{
			log.info(" handle Login excuted for "+ merchantnameOremail);
			Map<String,String>map=new HashMap<>();
			Merchant m=   merchantDaolayer.getByMerchantnameOrEmail(merchantnameOremail);
			try
			{
				if(m!= null && password.equals(m.getPassword()))
				{
					log.info(" credential verification is done successfully for "+ merchantnameOremail);
					String mid= m.getMerchant_id()+"";
						map.put("name",m.getMerchantname() );
						map.put("id",mid );
						return map;
				}
				else
				{
					log.info(" credential verification is Failed for "+ merchantnameOremail+ " due to invalid crdential");
					throw new PasswordInvalidException();				
				}
			}
			catch(PasswordInvalidException e)
			{
				log.info(" printing exception details for "+ merchantnameOremail);
				System.out.println(e.getMessage());
				return map;
			}
			
			
		}

		public boolean handleresetpassword(String newpassword, String email) 
		{
			log.info("excuting the handel reset passwird for merchant "+ email);
			Merchant merchant= merchantDaolayer.findByEmail(email);
			if(merchant!= null)
			{
			merchant.setPassword(newpassword);
			 merchantDaolayer.save(merchant);
			log.info(" Password reseted successfully for "+ email);
				return true;
			}
			else
			{
				log.error("password reset failed due to merchant object is null while saving.");
				return false;
			}
		}

		public Object verifymail(String email, String type) 
		{
			log.info("Verify email method excuted");
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
							log.info("OTP generated and sent to registerd email for merchant "+email);
							return otp;
						}
						else
						{
							log.error("Exception occured!! : Merchant already having account with mentioned email");
							throw new MerchantAlreadyExistsException();
						} 
					}
					catch (MerchantAlreadyExistsException exception)
					{
						log.info("Printing the exception description for merchant "+ email);
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
						log.info("OTP generated and sent to registerd email for merchant "+email);
						return otp;
					}
					else
					{
						log.error("Exception occured!! : Merchant not having account with mentioned email");
						throw new MerchantNotFoundException();
					}
				}
				catch (MerchantNotFoundException exception)
				{
					log.info("Printing the exception description for merchant "+ email);
					return exception.getMessage();
				}
			}
		}


		public Boolean verifyOTP(String email, int otp) 
		{
			log.info("Verify OTP excuted");
			Otp o=Otprepository.findByEmail(email);
			  
			if (otp == o.getOtp()) 
			{
				
				Otprepository.delete(o);
				log.info("OTP verified for merchant "+ email +" successfully");
				return true;
			} else 
			{
				log.info("OTP verifcation failed for merhant "+ email);
				return false;
			}

		}

		


		
}
