package com.book.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.book.entity.Book;
import com.book.exception.BookNotFoundException;
import com.book.exception.ErrorMessage;
import com.book.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	
	@PostMapping("/Book")
	public Book addBook(@RequestBody Book book) {
		return bookService.save(book);
		
		}
	
	
	@GetMapping("/books/{id}")
	public Book getBookById(@PathVariable int id) throws BookNotFoundException {
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
	
	@PutMapping("/books/{id}")
	public Book updateBookById(@Valid @RequestBody Book book , @PathVariable int id) throws BookNotFoundException {
		return bookService.updateBookById(book, id);
		
	}
	
	@PutMapping("/books")
	public Book updateBook(@RequestBody Book book) {
		Book updatedBook = bookService.save(book);
		return updatedBook;
	}
	
	@GetMapping("/booklist/{price}")
	public List<Book> getBooksByPrice(@PathVariable double price){
		return bookService.findBookByPrice(price);		
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
	
	
	
//	@ExceptionHandler(BookNotFoundException.class)
//	public ResponseEntity<String> handleEx(BookNotFoundException e){
//		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
//	}
	
//	@ExceptionHandler(BookNotFoundException.class)
//	public ResponseEntity<ErrorMessage> handleEx(BookNotFoundException e){
//		return  new ResponseEntity<ErrorMessage>(new ErrorMessage
//				(e.getMessage(),
//				LocalDateTime.now(),
//				e.getClass().toString()),
//				HttpStatus.NOT_FOUND);
//		
//}
}
