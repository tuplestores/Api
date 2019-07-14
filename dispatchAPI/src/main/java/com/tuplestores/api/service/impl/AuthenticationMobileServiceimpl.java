package com.tuplestores.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuplestores.api.dao.AuthenticationMobileDao;
import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.DriverModel;
import com.tuplestores.api.model.general.User;
import com.tuplestores.api.service.AuthenticationMobileService;

@Service("authenticationMobileService")
public class AuthenticationMobileServiceimpl implements AuthenticationMobileService{
	
	@Autowired
	AuthenticationMobileDao authenticationMobileDao;
	
	@Override
	public  DriverModel verifydriver(String isdCode, String mobile,String invite) {
		return authenticationMobileDao.verifydriver(isdCode,mobile,invite);	
	}
	
	@Override
	public DriverModel getDriverProfile(String driver_id) {
		

		return authenticationMobileDao.getDriverProfile(driver_id);
		
	}

	@Override
	public ApiResponse updateDriverProfile(String tenant_id, String driver_id, String email, String first_name,
			String last_name, String isd_code, String mobile) {
		
		
		return authenticationMobileDao.updateDriverProfile(tenant_id, driver_id, email,
				first_name, last_name, isd_code, mobile);
	}
	
	

	
}
