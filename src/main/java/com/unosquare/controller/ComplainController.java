package com.unosquare.controller;


import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unosquare.entity.Complain;
import com.unosquare.service.ComplainService;

@RestController
@RequestMapping("complain")
public class ComplainController {

	@Autowired
	ComplainService complainService;
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@PostMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Complain registerComplain(@RequestBody Complain complain) throws JMSException, JsonProcessingException {
		return complainService.registerComplain(complain);
	}
	
	@GetMapping(value="/",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Complain getComplain() throws Exception {
		return complainService.getComplain();
	}
	
	
}
