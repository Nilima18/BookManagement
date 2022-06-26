package com.book.exception;

import java.time.LocalDateTime;

public class ErrorMessage {
	
	private String message;
	private LocalDateTime time;
	private String exceptionType;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	@Override
	public String toString() {
		return "ErrorMessage [message=" + message + ", time=" + time + ", exceptionType=" + exceptionType + "]";
	}
	public ErrorMessage(String message, LocalDateTime time, String exceptionType) {
		super();
		this.message = message;
		this.time = time;
		this.exceptionType = exceptionType;
	}
 
	
}
