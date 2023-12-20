package com.employeewise.service;

import java.io.IOException;

import com.employeewise.entity.Employee;

import jakarta.mail.MessagingException;

public interface EmailService {

	public void sendEmail(Employee employee)throws MessagingException, IOException;
}
