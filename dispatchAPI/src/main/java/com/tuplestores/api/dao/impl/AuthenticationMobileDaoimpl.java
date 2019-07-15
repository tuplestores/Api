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
	public DriverModel verifydriver(String isdCode, String mobile,String invite) {
		CallableStatement callableStatement = null;
		Connection con = null;
		String out = "E";
		String tenant_id;
		DriverModel driver = null;
		
		try {
			
			driver = new DriverModel();
			con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
			
			//callableStatement = con.prepareCall("{call ap_sign_in_p(?,?)}");
			callableStatement = con.prepareCall("{call da_verify_driver_p(?,?,?,?,?,?)}");
			
			callableStatement.setString(1, isdCode);
			callableStatement.setString(2, mobile);
			callableStatement.setString(3, invite);
			callableStatement.registerOutParameter(4,java.sql.Types.VARCHAR); //driver id
			callableStatement.registerOutParameter(5,java.sql.Types.VARCHAR );// tenant id
			callableStatement.registerOutParameter(6,java.sql.Types.CHAR ); //valid
			callableStatement.executeUpdate();
			out = callableStatement.getString(6);
			String driver_id = callableStatement.getString(4);//driver id
			tenant_id=  callableStatement.getString(5);//tenant_id;
	
			driver.setDriver_id(driver_id);
			driver.setTenant_id(tenant_id);
			driver.setStatus(out);

			
		}
		catch(Exception e) {
			out = "E";
			
			driver.setStatus(out);
			e.printStackTrace();
		}finally {
			
			if(con!=null) {
				
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			if(callableStatement!=null) {
				
				try {
					callableStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return driver;
				
	}
	
	
	//------------get driver profile----------
	

		@Override
		public DriverModel getDriverProfile(String tenant_id, String driver_id) {
			{
				
				DriverModel driver = null;
				java.sql.CallableStatement callableStatement = null;
				Connection con = null;
				ResultSet rs = null;

				
				try {
					
					con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
					
					callableStatement = con.prepareCall("{call da_get_driver_details_p(?,?)}");
					
					callableStatement.setString(1,tenant_id);
					callableStatement.setString(2,driver_id);
					rs=callableStatement.executeQuery();
					
					
					if(rs.next()) {
						driver = new DriverModel();
						driver.setDriver_name(rs.getString("first_name"));
						driver.setDriver_email(rs.getString("email"));
						driver.setDriver_mobile(rs.getString("mobile"));
						driver.setIsd_code(rs.getString("isd_code"));
						driver.setTenant_id(tenant_id);
						driver.setDriver_id(driver_id);
					
						
					}
					
				}catch(Exception e) {
					driver = null;
					e.printStackTrace();
				}finally {
					if(rs!=null) {
						try {
							rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(callableStatement!=null) {
						try {
							callableStatement.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(con!=null)
						try {
							con.close();
							}catch (SQLException ex) {
								ex.printStackTrace();
							}
				}
				
					return driver;
			}

		}
		//--------------Update Driver Profile

		@Override
		public ApiResponse updateDriverProfile(String tenant_id,String driver_id, String email,
											   String first_name, String last_name,
											   String isd_code, String mobile) {
			java.sql.CallableStatement callableStatement = null;
			Connection con = null;
			ApiResponse api = null;
			String out = "E";
			try {
				api= new ApiResponse();
				con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
				callableStatement = con.prepareCall("{call ap_update_driver_p(?,?,?,?,?,?,?,?)}");
				callableStatement.setString(1, tenant_id);
				callableStatement.setString(2, driver_id);
				callableStatement.setString(3, email);
				callableStatement.setString(4, first_name);
				callableStatement.setString(5,last_name);
				callableStatement.setString(6, isd_code);
				callableStatement.setString(7, mobile);
				callableStatement.registerOutParameter(8, java.sql.Types.CHAR);
				callableStatement.executeUpdate();
				out = callableStatement.getString(8);
				api.setStatus(out);
				api.setMsg("Message");
				
			}catch(Exception e) {
				api.setStatus("E");
			
				e.printStackTrace();
			}finally {
				
				if(callableStatement!=null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
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
