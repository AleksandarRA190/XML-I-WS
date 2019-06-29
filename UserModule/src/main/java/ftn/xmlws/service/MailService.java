package ftn.xmlws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ftn.xmlws.dto.UserDTO;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	
	public void sendNotification(UserDTO user) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		//mail.setTo(user.getEmail());
		mail.setTo("duka96@sbb.rs");
		
		mail.setFrom("duleda.isa@gmail.com");
		mail.setSubject("Account verification for XmlWebServices web site");
		mail.setText("You have been successfully registered to XmlWebServices web site. "
				+ "Please go to the following link in order to verify your"
				+ " account: http://localhost:4200/activate" + user.getId());
		
		System.out.println("Pokusavam slanje...");
		
		mailSender.send(mail);
		
		System.out.println("Poslao!");
		
	}
	
}
