package com.employeewise.helper;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employeewise.entity.Employee;
import com.employeewise.repository.EmployeeRepository;

/*
 * This class contains helper methods
 */
@Component
public class Helper {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	/*
	 * Creates a unique string on random basis
	 * 
	 * @return: String -> Returns the created string
	 */
	public String createRandomString() {
		String newStringId = UUID.randomUUID().toString();
		return newStringId;
	}
	
	/*
	 * Build content for email body
	 * 
	 * @param: Employee -> An employee object
	 * 
	 * @return: String -> Customized message containing some of employee's details
	 */
	public String buildEmailContent(Employee employee) {
		
		String emailContent = employee.getEmployeeName()+" will now work under you."
				+ " Mobile number is ->"+ employee.getPhoneNumber()+" and email is "
//				+ "<mailto:" +employee.getEmail() + "|" + employee.getEmail() + ">.";
				+ employee.getEmail();
		return emailContent;
	}
	
	/*
	 * Method to get Employee who is manager from employeeId
	 * 
	 * @param: String -> EmployeeId
	 * 
	 * @return: Employee -> An Employee Object who is manager of given employee
	 */
	public Employee levelManager(String employeeId) {
		
		Optional<Employee> opt = employeeRepository.findById(employeeId);
		return opt.get();
	}
}
