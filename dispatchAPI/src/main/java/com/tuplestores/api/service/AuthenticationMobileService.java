package com.tuplestores.api.service;

import com.tuplestores.api.model.general.User;

public interface AuthenticationMobileService {
	User verifydriver(String isdCode, String mobile,String invite);
}
