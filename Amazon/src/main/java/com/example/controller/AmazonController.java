package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.Book;

@RestController
@RequestMapping("/Amazon")
public class AmazonController {
	
	//http://localhost:8989/Amazon
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks(){
		
		
		ResponseEntity<List<Book>> response = restTemplate.exchange("http://localhost:8081/Books", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<Book>>() {
		});
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	
	@PostMapping
	public String createBook(@RequestBody Book book) {
		HttpEntity<Book> entity = new HttpEntity<Book>(book);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081/Book", HttpMethod.POST, entity, String.class);
		return response.getBody();		
	}
	
	@PutMapping
	public ResponseEntity<Book> updateBook(@RequestBody Book book){
		HttpEntity<Book> request=new HttpEntity<>(book);
		ResponseEntity<Book> response =restTemplate.exchange("http://localhost:8081/books", HttpMethod.PUT, request, Book.class);
		return response;
	}
	

}
