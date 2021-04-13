package com.unosquare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.unosquare.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	

      User findByUserName(String userName);
	
}
