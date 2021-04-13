package com.unosquare.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.unosquare.dao.BookDAO;
import com.unosquare.dao.UserDAO;
import com.unosquare.entity.Book;
import com.unosquare.entity.User;

@Service
public class BookService {
	
	@Autowired
	BookDAO bookDAO;
	
	@Autowired
	UserDAO userDAO;
	
	public Page<Book> getBooks(Integer page, Integer size) {
		return bookDAO.getBooks(page, size);
	}
	
	public Book saveBook(Book book) throws Exception {
		if(book.getId()!=null) {
			throw new javax.persistence.EntityNotFoundException("Operación no permitida");
		}
		
		User user = userDAO.findByUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		book.setUserID(user.getId());
		book.setState(true);
		
		return bookDAO.saveBook(book);
	}
	
	public Book interchangeBook(String bookId) {
		if(bookId==null) {
			return null;
		}
		Book book = bookDAO.findBook(bookId);
		User userSend = userDAO.findById(book.getUserID());
		User userReceive = userDAO.findByUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		
		if(book.getState() && !userSend.getId().equals(userReceive.getId())) {
		   book.setUserID(userReceive.getId());
		   
		   if(userSend.getBookSended()==null) {
			   List<String> list = new ArrayList<>();
			   list.add(bookId);
			   userSend.setBookSended(list);
		   } else {
			   List<String> list = userSend.getBookSended();
			   list.add(bookId);
			   userSend.setBookSended(list);
		   }
		   
		   if(userReceive.getBookReceived()==null) {
			   List<String> list = new ArrayList<>();
			   list.add(bookId);
			   userReceive.setBookReceived(list);
		   } else {
			   List<String> list = userReceive.getBookReceived();
			   list.add(bookId);
			   userReceive.setBookReceived(list);
		   }
		   userDAO.saveUser(userReceive);
		   userDAO.saveUser(userSend);
		   book.setState(false);
		   bookDAO.saveBook(book);
		   return book;
		   
		}
	
		
		return null;
	}
	
	public Book deleteBook(String bookId) {
		if(bookId==null) {
			return null;
		}
		Book book = bookDAO.findBook(bookId);
		User user = userDAO.findByUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		
		if(book.getUserID().equals(user.getId())) {
			book.setState(false);
			book = bookDAO.saveBook(book);
			return book;
		}
		
		return null;
	}

}
