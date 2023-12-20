package com.employeewise.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeewise.entity.Employee;
import com.employeewise.exception.EmailAlreadyExistsException;
import com.employeewise.exception.EmployeeDoesNotExistsException;
import com.employeewise.exception.EmployeeHasNoManagerException;
import com.employeewise.exception.InvalidInputException;
import com.employeewise.service.EmployeeService;

import jakarta.mail.MessagingException;

/*
 * This class is responsible for handling HTTP requests 
 * related to employee management
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addEmployeeHandler(@RequestBody Employee employee) throws EmailAlreadyExistsException, MessagingException, IOException{
		String res = employeeService.addEmployee(employee);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployeehandler(){
		List<Employee> list = employeeService.getAllEmployee();
		return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeByIdHandler(@PathVariable String id) throws EmployeeDoesNotExistsException{
		String res = employeeService.deleteEmployeeById(id);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updatedEmployeeHandler(@PathVariable String id, @RequestBody Employee employee) throws EmailAlreadyExistsException, EmployeeDoesNotExistsException{
		String res = employeeService.updateEmployeeDetails(id,employee);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getManager/{id}/{level}")
	public ResponseEntity<Employee> getNthLevelManagerHandler(@PathVariable String id, @PathVariable int level) throws EmployeeDoesNotExistsException, InvalidInputException, EmployeeHasNoManagerException{
		Employee res = employeeService.getNthLevelManagerOfEmployee(id, level);
		return new ResponseEntity<Employee>(res,HttpStatus.OK);
	}
	
	@GetMapping("/allCustom")
	public ResponseEntity<List<Employee>> getAllEmployeeCustomHandler(@RequestParam int page,@RequestParam int size,@RequestParam String sortBy){
		List<Employee> list = employeeService.getAllEmployees(page, size, sortBy);
		return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
	}
}
