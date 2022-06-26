package com.book.repo;

import org.springframework.stereotype.Repository;

import com.book.entity.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface BookRepository extends JpaRepository<Book , Integer>{
	
	
@Query(value="select b from Book b where b.price>?1", nativeQuery =false)
public List<Book> findBookByprice(double price) ;	
	 


}


