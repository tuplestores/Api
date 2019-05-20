//Commented by Ajish
package com.tuplestores.api.service;

import com.tuplestores.api.model.general.User;

//commented by ajish
public interface AuthenticationService {

	User SignIn(String email, String password); 
}
