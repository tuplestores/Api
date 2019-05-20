//Authentication Service
//Written By Ajish Dharman
//Dated 20-05-2019
package com.tuplestores.api.service;

import com.tuplestores.api.model.general.User;


public interface AuthenticationService {

	User SignIn(String email, String password); 
}
