package com.book.service;


import java.util.List;

import com.book.entity.Book;
import com.book.exception.BookNotFoundException;



public interface BookService {
	
	Book save(Book book);
	void deletebyId(int id) throws BookNotFoundException;
	Book updateBookById(Book book , int id) throws BookNotFoundException ;
	Book updateBook(Book book);
	List<Book> getAllBooks();
	Book findBookById(int id) throws BookNotFoundException ;
	List<Book> findBookByPrice(double price);
	
	

}
