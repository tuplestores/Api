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
import com.tuplestores.api.model.general.DriverModel;
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
	
	
	//------------get driver profile----------
	

		@Override
		public DriverModel getDriverProfile(String driver_id) {
			{
				
				DriverModel driver = null;
				java.sql.CallableStatement callableStatement = null;
				Connection con = null;
				ResultSet rs = null;

				
				try {
					con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
					callableStatement = con.prepareCall("{call ap_list_vehicle_p(?)}");
					
					callableStatement.setString(1,driver_id);
					rs=callableStatement.executeQuery();
					
					
					if(rs.next()) {
						driver = new DriverModel();
						driver.setDriver_name(rs.getString("Driver_Name"));
						driver.setDriver_email(rs.getString("Driver_email"));
						driver.setDriver_mobile(rs.getString("Driver_Mobile"));
						driver.setInvite_code(rs.getString("Invite_code"));
						driver.setIsd_code(rs.getString("String"));
					
						
					}
					
				}catch(Exception e) {
					driver = null;
					e.printStackTrace();
				}finally {
					if(con!=null)
						try {
							con.close();
						}catch(SQLException ex) {
							ex.printStackTrace();
						}
				}
				
					return driver;
			}

		}
		//--------------Update Driver Profile

		@Override
		public ApiResponse updateDriverProfile(String driver_id, String email,
				String first_name, String last_name,
				String isd_code, String mobile) {
			java.sql.CallableStatement callableStatement = null;
			Connection con = null;
			ApiResponse api = null;
			String out = "E";
			try {
				con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
				callableStatement = con.prepareCall("{}");
				callableStatement.setString(1, driver_id);
				callableStatement.setString(2, email);
				callableStatement.setString(3, first_name);
				callableStatement.setString(4,last_name);
				callableStatement.setString(5, isd_code);
				callableStatement.setString(6, mobile);
				api.setStatus("Status");
				api.setMsg("Message");
				
			}catch(Exception e) {
				api.setMsg("Message");
				api.setStatus("Status");
				e.printStackTrace();
			}finally {
				if(con!=null)
					try {
						con.close();
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
			}
			
			return api;
		}

}
