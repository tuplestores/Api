package com.tuplestores.api.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuplestores.api.dao.AuthenticationMobileDao;
import com.tuplestores.api.dao.DispatchDBConnection;
import com.tuplestores.api.model.general.User;

@Repository("authenticationMobileDao")
public class AuthenticationMobileDaoimpl implements AuthenticationMobileDao{
	
	@Autowired
	DispatchDBConnection dispatchDBConnection;
		
		
	@Override
	public User verifydriver(String isdCode, String mobile,String invite) {
		User user=null;
		CallableStatement callableStatement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			callableStatement = con.prepareCall("(call da_verify_driver_p)");
			
			callableStatement.setString(1, isdCode);
			callableStatement.setString(2, mobile);
			callableStatement.setString(3, invite);
			rs = callableStatement.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setIsdCode(rs.getString("ISD Code"));
				user.setMobile(rs.getString("Mobile"));
				user.setInvite(rs.getString("Invite"));
			}
		}
		catch(Exception e) {
			user=null;
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
		}
		return user;
				
	}

}
