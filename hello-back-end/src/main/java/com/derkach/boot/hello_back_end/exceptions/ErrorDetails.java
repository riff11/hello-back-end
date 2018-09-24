package com.derkach.boot.hello_back_end.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private HttpStatus status;
	private String path;
	
	
	
	public ErrorDetails(Date timestamp, String message, HttpStatus status, String path) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.path = path;
	}
	
	
	public Date getTimestamp() {
		return timestamp;
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
