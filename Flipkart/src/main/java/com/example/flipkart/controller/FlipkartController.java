package com.example.flipkart.controller;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.entity.Book;


@RestController
@RequestMapping("/flipkart")
public class FlipkartController {
	
private RestTemplate restTemplate;
	
	
	@Autowired
	public FlipkartController(RestTemplateBuilder restTemplateBuilder) {
		super();
		this.restTemplate = restTemplateBuilder.build();
	}

	
	
//	@PostMapping
//	public Book createBook(Book book) {
//Book newBook= new Book( "Moon", "Rey", 60);
//		
//		Book result = restTemplate.postForObject("http://localhost:8081/Book", newBook, Book.class);
//		return result;
//	
//		//return "New Book saved";
//	}

	@PostMapping("/book")
	public String createBook(@RequestBody Book book) {
		HttpEntity<Book> entity = new HttpEntity<Book>(book);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8082/Book/Book", HttpMethod.POST, entity, String.class);
		return response.getBody();		
	}
	
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBook(){
		
		ResponseEntity<List<Book>> result= restTemplate.exchange("http://localhost:8082/Book/Books", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<Book>>() {
		});
		return ResponseEntity.status(HttpStatus.OK).body(result.getBody());
	}
	
	@PutMapping
	public ResponseEntity<Book> updateBook(@RequestBody Book book){
		HttpEntity<Book> request=new HttpEntity<>(book);
		ResponseEntity<Book> response =restTemplate.exchange("http://localhost:8082/Book/books", HttpMethod.PUT, request, Book.class);
		return response;
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable ("id") int id){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Book> entity = new HttpEntity<Book>(headers);
		ResponseEntity<String> response =restTemplate.exchange("http://localhost:8082/books/" +id, HttpMethod.DELETE, entity, String.class);
		return response.getBody();
	}
	
	@GetMapping("/{id}")
	public String getBookById(@PathVariable ("id") int id){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Book> entity = new HttpEntity<Book>(headers);
		ResponseEntity<String> response =restTemplate.exchange("http://localhost:8082/books/" +id, HttpMethod.GET, entity, String.class);
		return response.getBody();
	}
	
	

}
