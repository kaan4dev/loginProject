package kc.loginUI.email;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service

public class EmailService implements EmailSender
{
	private final static Logger LOGGER=org.slf4j.LoggerFactory.getLogger(EmailService.class);
	
	private JavaMailSender javaMailSender;

	public EmailService()
	{
		
	}
	
	public EmailService(JavaMailSender javaMailSender) 
	{
		this.javaMailSender = javaMailSender;
	}

	@Async
	public void send(String to, String email) 
	{
		try 
		{
			MimeMessage mimeMessage=javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,"utf-8");
			
			mimeMessageHelper.setText(email,true);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject("Confirm your email");
			mimeMessageHelper.setFrom("paul@dotmail.com");
		}
		catch (MessagingException e) 
		{
			LOGGER.error("failed to send e-mail!",e);
			
			throw new IllegalStateException("failed to sent e-mail!");
		}
	}
	
}
