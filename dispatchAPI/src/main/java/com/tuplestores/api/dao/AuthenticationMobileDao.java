package com.tuplestores.api.dao;

import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.User;

public interface AuthenticationMobileDao {
	ApiResponse verifydriver(String isdCode, String mobile,String invite);
}
