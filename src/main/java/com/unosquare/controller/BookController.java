package com.unosquare.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unosquare.entity.Book;
import com.unosquare.service.BookService;

@RestController
@RequestMapping("book")
public class BookController {

	@Autowired
	BookService bookService;
	
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Book> getBooks(@RequestParam Integer page, @RequestParam Integer size) {
		return bookService.getBooks(page, size);
	}
	
	@PostMapping(value="/",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book saveBook(@RequestBody Book book) throws Exception {
		return bookService.saveBook(book);
	}
	
	@DeleteMapping(value="",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book deleteBook(@RequestParam String book) throws Exception {
		return bookService.deleteBook(book);
	}
	
	@PostMapping(value="/interchange",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book interchange(@RequestParam String book) throws Exception {
		return bookService.interchangeBook(book);
	}
	
}
