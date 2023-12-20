package com.employeewise.service;

import java.io.IOException;
import java.util.List;

import com.employeewise.entity.Employee;
import com.employeewise.exception.EmailAlreadyExistsException;
import com.employeewise.exception.EmployeeDoesNotExistsException;
import com.employeewise.exception.EmployeeHasNoManagerException;
import com.employeewise.exception.InvalidInputException;

import jakarta.mail.MessagingException;

/*
 * This interface consists all methods signature related to employee
 */
public interface EmployeeService {

	public String addEmployee(Employee employee) throws EmailAlreadyExistsException, MessagingException, IOException;
	
	public List<Employee> getAllEmployee();
	
	public String deleteEmployeeById(String employeeId) throws EmployeeDoesNotExistsException;
	
	public String updateEmployeeDetails(String employeeId,Employee employee)throws EmailAlreadyExistsException,EmployeeDoesNotExistsException;
	
	public Employee getNthLevelManagerOfEmployee(String employeeId,int n) throws EmployeeDoesNotExistsException, InvalidInputException, EmployeeHasNoManagerException;
	
	public List<Employee> getAllEmployees(int page,int size,String sortBy);
}
