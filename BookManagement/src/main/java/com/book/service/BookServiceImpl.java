package com.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.exception.BookNotFoundException;
import com.book.repo.BookRepository;


@Service
@EnableCaching
public class BookServiceImpl implements BookService{
	
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void deletebyId(int id) throws BookNotFoundException {
		Optional <Book> optional=bookRepository.findById(id);
		if(optional.isEmpty()) {
			throw new BookNotFoundException("Book with id:"+ id + " Not available ");
		}
      bookRepository.deleteById(id);		
	}

	@Override
	public Book updateBookById(Book book, int id) throws BookNotFoundException {
	
			
			 Optional <Book> existingbook=bookRepository.findById(id);
			 if(existingbook.isEmpty()) {
				 throw new BookNotFoundException("Book with id:"+ id + " Not available ");
			 }

				 Book book1 = existingbook.get();
				 book1.setTitle(book.getTitle());
				 book1.setAuthor(book.getAuthor());
				 book1.setPrice(book.getPrice());
				 bookRepository.save(book1);
					return book1;
					 }
				


	@Override
	@Cacheable(key ="#id" , value="book-store")
	public Book findBookById(int id) throws BookNotFoundException {
		 Optional <Book> existingbook=bookRepository.findById(id);
		 if(existingbook.isEmpty()) {
			 throw new BookNotFoundException("Book with id:" + id + " Not available ");
		 }

		
		 return existingbook.get();
		
	}
	
	
	

	@Override
	public List<Book> findBookByPrice(double price) {
		return bookRepository.findBookByprice(price);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	@Override
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}


	
}
