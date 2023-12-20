package com.employeewise.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employeewise.entity.Employee;
import com.employeewise.exception.EmailAlreadyExistsException;
import com.employeewise.exception.EmployeeDoesNotExistsException;
import com.employeewise.exception.EmployeeHasNoManagerException;
import com.employeewise.exception.InvalidInputException;
import com.employeewise.helper.Helper;
import com.employeewise.repository.EmployeeRepository;

import jakarta.mail.MessagingException;

/*
 * This class contains business logics for the application
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private Helper helper;
	
	@Autowired
	private EmailService emailService;
	
	/*
	 * Add Employee to DB and send email to manager 
	 * 
	 * @param: Employee -> An employee object to save
	 * 
	 * @return: String -> A string consisting unique employeeId
	 * 
	 * @throws: EmailAlreadyExistsException -> Throws this exception if given email already 
	 * exists in database with another user
	 */
	@Override
	public String addEmployee(Employee employee) throws EmailAlreadyExistsException, MessagingException, IOException {
		
		Optional<Employee> opt = employeeRepository.findByEmail(employee.getEmail());
		if (opt.isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists!!!");
		}
		else {
			employee.setEmployeeId(helper.createRandomString());
			Employee savedEmployee = employeeRepository.save(employee);
			if (savedEmployee.getReportTo() != null) {
				emailService.sendEmail(savedEmployee);
			}
			
			return savedEmployee.getEmployeeId();
		}
	}

	/*
	 * Get all Employees
	 * 
	 * @return: List<Employee> -> Returns List of all employees. In case no employee found then 
	 * returns empty list
	 */
	@Override
	public List<Employee> getAllEmployee() {
		
		return employeeRepository.findAll();
	}

	/*
	 * Delete Employee by EmployeeId
	 * 
	 * @param: EmployeeId -> Unique id of the employee
	 * 
	 * @return: String -> A customized positive response with employeeId
	 * 
	 * @throws: EmployeeDoesNotExistsException -> Throws this exception when no employee
	 * found with given employeeId in database
	 */
	@Override
	public String deleteEmployeeById(String employeeId) throws EmployeeDoesNotExistsException {
		
		Optional<Employee> opt = employeeRepository.findById(employeeId);
		if (opt.isEmpty()) {
			throw new EmployeeDoesNotExistsException("Employee with chosen id-> "+employeeId+" doesNot exists");
		}
		else {
			employeeRepository.delete(opt.get());
			return "Employee with id -> "+employeeId+" has been deleted successfully";
		}
	}

	/*
	 * Update Employee Details
	 * 
	 * @param: String -> Unique id of Employee
	 * @param: Employee -> Employee object with details that user wants to update
	 * 
	 * @return: String -> A positive response consisting employeeId
	 * 
	 * @throws: EmployeeDoesNotExistsException -> Throws this exception when no employee found
	 * with given employeeId
	 * 
	 * @throws: EmailAlreadyExistsException -> Throws this exception when email which user has provided 
	 * for updation belongs to other user and already exists in database
	 */
	@Override
	public String updateEmployeeDetails(String employeeId,Employee employee)
			throws EmailAlreadyExistsException, EmployeeDoesNotExistsException {
		
		Optional<Employee> opt = employeeRepository.findById(employeeId);
		if (opt.isEmpty()) {
			throw new EmployeeDoesNotExistsException("Employee Not found with given Id!!!");
		}
		else {
			Employee foundEmployee = opt.get();
			if (employee.getEmail() != null) {
				if (employee.getEmail().equals(foundEmployee.getEmail())) {
					foundEmployee.setEmail(employee.getEmail());
				}
				else {
					if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
						throw new EmailAlreadyExistsException("Provided email already exists!! Try different email for updation");
					}
					else {
						foundEmployee.setEmail(employee.getEmail());
					}
				}
			}
			if (employee.getEmployeeName() != null) {
				foundEmployee.setEmployeeName(employee.getEmployeeName());
			}
			if (employee.getPhoneNumber() != null) {
				foundEmployee.setPhoneNumber(employee.getPhoneNumber());
			}
			if (employee.getReportTo() != null) {
				foundEmployee.setReportTo(employee.getReportTo());
			}
			if (employee.getUrl() != null) {
				foundEmployee.setUrl(employee.getUrl());
			}
			employeeRepository.save(foundEmployee);
			return "Details updated Successfully for EmployeeId -> "+employeeId;
			
		}
		
	}

	/*
	 * Get nth Level manager for Employee
	 * 
	 * @param: String -> EmployeeId
	 * @param: int -> no of level
	 * 
	 * @return: Employee -> Returns nth level manager of employee
	 * 
	 * @throws: EmployeeDoesNotExistsExceptio -> Throws this exception if no employee 
	 * found with given employeeId
	 * @throws: InvalidInputException -> Throws this exception if input passed as level is < 0
	 * @throws : EmployeeHasNoManagerException -> Throws this exception if there is no manager found
	 * on nth level for employee
	 */
	@Override
	public Employee getNthLevelManagerOfEmployee(String employeeId, int n) throws EmployeeDoesNotExistsException, InvalidInputException, EmployeeHasNoManagerException {
		
		if (n < 1) {
	        throw new InvalidInputException("Level can not be 0!!");
	    }

	    Optional<Employee> opt = employeeRepository.findById(employeeId);
	    if (opt.isEmpty()) {
	        throw new EmployeeDoesNotExistsException("Employee Not found with given Id!!!");
	    }

	    Employee foundEmployee = opt.get();
	    String managerId = foundEmployee.getReportTo();

	    if (managerId == null) {
	        throw new EmployeeHasNoManagerException("No Manager found for Employee");
	    }

	    Optional<Employee> opt2 = employeeRepository.findById(managerId);
	    if (opt2.isEmpty()) {
	        throw new EmployeeDoesNotExistsException("Manager Not found with given Id!!!");
	    }

	    Employee foundManager = opt2.get();
	    Employee manager;

	    if (n == 1) {
	        manager = foundManager;
	    } else {
	        // Using the result of the recursive call to set the manager variable
	        manager = getNthLevelManagerOfEmployee(managerId, n - 1);
	    }

	    return manager;
	}

	/*
	 * Get all employees with pagination and sorting
	 * 
	 * @param: int -> pages
	 * @param: int -> size of data
	 * @param: String -> sorting parameter
	 * 
	 * @return: List<Employees> -> returns list of all employees that follow all criteria
	 */
	@Override
	public List<Employee> getAllEmployees(int page, int size, String sortBy) {
		
		PageRequest pageable = PageRequest.of(page, size,Sort.by(sortBy));
		Page<Employee> employeePage = employeeRepository.findAll(pageable);
		return employeePage.getContent();
	}

}
