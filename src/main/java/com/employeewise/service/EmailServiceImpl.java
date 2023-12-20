package com.employeewise.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.employeewise.entity.Employee;
import com.employeewise.helper.Helper;

import jakarta.mail.MessagingException;

/*
 * This class contains business logic related to sending email
 */
@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Helper helper;
	
	/*
	 * Send Email
	 * 
	 * @param: Employee -> An employee object
	 * 
	 * @throws: MessagingException, IOException -> Throws follosing exceptions in case 
	 * of any error
	 */
	@Override
	public void sendEmail(Employee employee) throws MessagingException, IOException {
		
		Employee manager = helper.levelManager(employee.getReportTo());
		String toEmail = manager.getEmail();
		String toName = manager.getEmployeeName();
		String message = helper.buildEmailContent(employee);
		String subject = "New Employee Addition";
			
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setFrom("nnorth87@gmail.com");
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		javaMailSender.send(mailMessage);
		
		
	}
	
	

}
