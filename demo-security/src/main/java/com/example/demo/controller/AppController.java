package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	
	
	@GetMapping("/hello")
	public String greet() {
		return "welcome";
		
	}
	
	@GetMapping("/Hi")
	public String hello() {
		return "Hello";
		
	}

}
