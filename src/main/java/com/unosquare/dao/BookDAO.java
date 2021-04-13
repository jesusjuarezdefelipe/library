package com.unosquare.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.unosquare.entity.Book;
import com.unosquare.repository.BookRepository;


@Component
public class BookDAO {
	
	@Autowired
	BookRepository bookRepository;
	
	public Page<Book> getBooks(Integer page, Integer size) {
		return bookRepository.findAllByState(true, PageRequest.of(page, size));
	}

	public Book saveBook(Book book) {
	    return bookRepository.save(book);
	}
	
	public Book findBook(String id) {
	    return bookRepository.findById(id).get();
	}
}
