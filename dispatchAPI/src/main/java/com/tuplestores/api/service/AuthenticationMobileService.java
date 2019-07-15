package com.tuplestores.api.service;

import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.DriverModel;
import com.tuplestores.api.model.general.User;

public interface AuthenticationMobileService {
	DriverModel verifydriver(String isdCode, String mobile,String invite);
	
	DriverModel getDriverProfile(String tenant_id, String driver_id);
	ApiResponse updateDriverProfile(String tenant_id, String driver_id,String email,String first_name,
									String last_name,String isd_code,String mobile);
}
