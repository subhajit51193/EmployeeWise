package com.employeewise.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Class defines an entity for an employee
 */
@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

	@Id
	private String employeeId;
	private String employeeName;
	private String phoneNumber;
	private String email;
	private String reportTo;
	private String url;
}
