package com.tuplestores.api.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuplestores.api.dao.AuthenticationMobileDao;
import com.tuplestores.api.dao.DispatchDBConnection;
import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.Driver;
import com.tuplestores.api.model.general.User;

@Repository("authenticationMobileDao")
public class AuthenticationMobileDaoimpl implements AuthenticationMobileDao{
	
	@Autowired
	DispatchDBConnection dispatchDBConnection;
		
		
	@Override
	public ApiResponse verifydriver(String isdCode, String mobile,String invite) {
		User user=null;
		CallableStatement callableStatement = null;
		Connection con = null;
		String out = "E";
		ApiResponse api = null;
		Driver driver = null;
		String driverid=null;
		try {
			api = new ApiResponse();
			callableStatement = con.prepareCall("(call da_verify_driver_p)");
			
			callableStatement.setString(1, isdCode);
			callableStatement.setString(2, mobile);
			callableStatement.setString(3, invite);
			callableStatement.registerOutParameter(4,java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(5,java.sql.Types.VARCHAR );
			callableStatement.executeUpdate();
			out = callableStatement.getString(4);
			driverid = callableStatement.getString(5);
			api.setStatus(out);
			api.setMsg("SUCCESS");
			driver.setDriver_id(driverid);

			
		}
		catch(Exception e) {
			out = "E";
			api.setStatus(out);
			api.setMsg("Failure");
			driver.setDriver_id("Failure");
			e.printStackTrace();
		}finally {
			
			if(con!=null)
				try {
					con.close();
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
		}
		return api;
				
	}

}
