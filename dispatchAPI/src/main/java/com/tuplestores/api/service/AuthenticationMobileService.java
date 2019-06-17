package com.tuplestores.api.service;

import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.User;

public interface AuthenticationMobileService {
	ApiResponse verifydriver(String isdCode, String mobile,String invite);
}
