package com.tuplestores.api.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuplestores.api.model.general.User;
import com.tuplestores.api.service.AuthenticationService;

<<<<<<< HEAD
=======
//Controller for authentication purpose
// Created by Ajish 

>>>>>>> branch 'master' of https://github.com/tuplestores/Api.git
@Controller
public class AuthenticationController {

	@Autowired
	AuthenticationService authenticationService;
//thisaaa
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public @ResponseBody Object signin(@RequestHeader String email,@RequestHeader String password,
			HttpSession session,
			HttpServletRequest request) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		HttpHeaders httpHeaders = new HttpHeaders();
		try {
			//password=EncryptPassword.encrypt(password);
			User user=authenticationService.SignIn(email,password);
			map.put("user", user);	
			//aasdasdsa
		} catch (Exception e) {
			map.put("user", null);
			e.printStackTrace();
		}		
		return new ResponseEntity<Map>(map,httpHeaders, HttpStatus.OK);	
	}	
	
	
}
