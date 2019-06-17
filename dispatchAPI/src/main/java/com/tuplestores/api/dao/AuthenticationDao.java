package com.tuplestores.api.dao;

import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.User;

public interface AuthenticationDao {

	User SignIn(String email, String password); 
}
