package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Book;
import com.example.service.BookService;


@RestController
@RequestMapping("/Book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	
	@PostMapping("")
	public Book addBook(@RequestBody Book book) {
		return bookService.save(book);
		
		}
	
	
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable int id) {
		return bookService.findBookById(id);
		
		}
	
	@DeleteMapping("/books/{id}")
	public void deleteBookById(@PathVariable ("id") int id) {
		bookService.deletebyId(id);
	
		}
	
	@GetMapping("/Books")
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
		
		}
	
	
	
	@PutMapping("")
	public Book updateBook(@RequestBody Book book) {
		Book updatedBook = bookService.save(book);
		return updatedBook;
	}
	
	
	

	@ResponseStatus(code =HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleException(MethodArgumentNotValidException exception){
		Map <String,String> messages = new HashMap<>();
		exception.getAllErrors().forEach(error ->{
			String fieldName = ((FieldError)error).getField();
			String errorMessage = ((FieldError)error).getDefaultMessage();
			messages.put(fieldName, errorMessage);
	});
	return messages;
	}
}
