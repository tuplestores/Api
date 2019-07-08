package com.tuplestores.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuplestores.api.dao.AuthenticationDao;
import com.tuplestores.api.model.general.User;
import com.tuplestores.api.service.AuthenticationService;


@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	AuthenticationDao authenticationDao;
	
	
	
	@Override
	public User SignIn(String email, String password) {
		
		
		return authenticationDao.SignIn(email, password);
	
		
	}//Signin

	

	
}
