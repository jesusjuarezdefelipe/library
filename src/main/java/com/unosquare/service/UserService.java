package com.unosquare.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.unosquare.dao.UserDAO;
import com.unosquare.entity.User;
import com.unosquare.exception.UserRepeatedException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder cryp;
	
	public User login(String username, String pwd) throws Exception {

		User user = userDAO.findByUserName(username);
		if(user == null) {
			throw new javax.persistence.EntityNotFoundException("Operación no permitida");
		}
						
		if(cryp.matches(pwd, user.getPassword()) && user.getEnabled()) {
		
		   user.setToken(getJWTToken(username, user.getRole()));		
		   user.setPassword(null);
		
		} else {
			throw new javax.persistence.EntityNotFoundException("Operación no permitida");
		}
		
		return user;
	}

	public User saveUser(User user) throws Exception {
		if(user.getUserName()==null || user.getPassword()==null) {
			throw new javax.persistence.EntityNotFoundException("Operación no permitida");
		}
		
		if(userDAO.findByUserName(user.getUserName())!=null) {
			user.setPassword(cryp.encode(user.getPassword()));
			user.setRole("ROLE_USER");
			user = userDAO.saveUser(user);
			user.setPassword(null);
		} else {
			throw new UserRepeatedException("Usuario existente");
		}
		
		
		return user;
	}
	
	public User updateStatus(User user) throws Exception {
		if(user.getEnabled()==null) {
			throw new javax.persistence.EntityNotFoundException("Operación no permitida");
		}
		User aux = userDAO.findByUserName(user.getUserName());
		aux.setEnabled(user.getEnabled());
		return userDAO.saveUser(aux);
	}
	
	private String getJWTToken(String username, String role) {
		String secretKey = "LibraryUNOSQUARE2021";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(role);
		
		return Jwts
				.builder()
				.setId("unosquare")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

	}

}
