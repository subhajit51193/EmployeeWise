package com.employeewise.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employeewise.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String>{

	public Optional<Employee> findByEmail(String email);
}
