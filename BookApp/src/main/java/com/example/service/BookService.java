package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.entity.Book;
import com.example.repository.BookRepository;



@Service
public class BookService {

	
	@Autowired
	private BookRepository bookRepository;

	
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	
	public void deletebyId(int id)  {
		Optional <Book> optional=bookRepository.findById(id);
		if(optional.isPresent()) {
				bookRepository.deleteById(id);
	}
	}

	
	public Book updateBookById(Book book, int id)  {
	
			
			 Optional <Book> existingbook=bookRepository.findById(id);
			 if(existingbook.isEmpty()) {
				 return null;
			 }

				 Book book1 = existingbook.get();
				 book1.setTitle(book.getTitle());
				 book1.setAuthor(book.getAuthor());
				 book1.setPrice(book.getPrice());
				 bookRepository.save(book1);
					return book1;
					 }
				


	
	@Cacheable(key ="#id" , value="book-store")
	public Book findBookById(int id)  {
		 Optional <Book> existingbook=bookRepository.findById(id);
		 if(existingbook.isPresent()) {

			 return existingbook.get();
			
		 }
		
		 return null;
		
	}
	
	
	

	public List<Book> getAllBooks(){
		 return bookRepository.findAll();
	}
	
}
