package com.tuplestores.api.dao;

import com.tuplestores.api.model.general.User;

public interface AuthenticationMobileDao {
	User verifydriver(String isdCode, String mobile,String invite);
}
