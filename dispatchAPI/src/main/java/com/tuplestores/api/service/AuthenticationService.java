package com.tuplestores.api.service;

import com.tuplestores.api.model.general.User;

public interface AuthenticationService {

	User SignIn(String email, String password); 
}
