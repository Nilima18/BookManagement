package com.book.exception;

public class BookNotFoundException extends Exception{
	
	public  BookNotFoundException () {}
	public  BookNotFoundException (String s) {
		super(s);
	}
	public  BookNotFoundException (Exception e) {
		super(e);
	}
	public  BookNotFoundException (String s,Exception e) {
		super(s,e);
	}

}
