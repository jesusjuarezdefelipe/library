package com.unosquare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.unosquare.entity.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, String> {

	 Page<Book> findAllByState(Boolean state, Pageable pageable);
}
