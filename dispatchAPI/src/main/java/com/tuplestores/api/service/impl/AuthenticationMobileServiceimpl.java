package com.tuplestores.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuplestores.api.dao.AuthenticationMobileDao;
import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.User;
import com.tuplestores.api.service.AuthenticationMobileService;

@Service("authenticationService")
public class AuthenticationMobileServiceimpl implements AuthenticationMobileService{
	
	@Autowired
	AuthenticationMobileDao authenticationMDao;
	
	@Override
	public  ApiResponse verifydriver(String isdCode, String mobile,String invite) {
		return authenticationMDao.verifydriver(isdCode,mobile,invite);	
	}
	
}
