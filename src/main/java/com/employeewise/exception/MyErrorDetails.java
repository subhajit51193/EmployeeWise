package com.employeewise.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * This class is used for personalized error details in case exception is thrown
 * within the application
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyErrorDetails {

	private LocalDateTime timestamp;
	private String message;
	private String details;
}
