package com.derkach.boot.hello_back_end.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

/**
 * bean for ResponseEntity
 * 
 * @author alex
 *
 */
public class ErrorDetails {
	private LocalDateTime time;
	private String message;
	private HttpStatus status;
	private String path;

	public ErrorDetails(String message, HttpStatus status, String path) {
		super();
		this.time = LocalDateTime.now();
		this.message = message;
		this.status = status;
		this.path = path;
	}
	
	public ErrorDetails() {
		
	}

	public LocalDateTime getTime() {
		return time;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getPath() {
		return path;
	}

}
