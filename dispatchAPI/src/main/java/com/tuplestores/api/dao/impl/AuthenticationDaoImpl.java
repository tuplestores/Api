package com.tuplestores.api.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuplestores.api.dao.AuthenticationDao;
import com.tuplestores.api.dao.DispatchDBConnection;
import com.tuplestores.api.model.general.User;


@Repository("authenticationDao")
public class AuthenticationDaoImpl implements AuthenticationDao {

	@Autowired
	DispatchDBConnection dispatchDBConnection;
		
		
	@Override
	public User SignIn(String email, String password) {
		
		User user=null;
		CallableStatement callableStatement = null;
		Connection con =null;
		ResultSet rs=null;
		try {
			//ap_list_admin_users_p
			con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
			callableStatement = con.prepareCall("{call ap_sign_in_p(?,?)}");
			
			//callableStatement = con.prepareCall("{call ap_list_admin_users_p(?)}");
			
			callableStatement.setString(1,email);
			callableStatement.setString(2,password);
		//	callableStatement.setString(3,"");
			rs=callableStatement.executeQuery();
			
			if(rs.next()){

				user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setEmail(rs.getString("email"));
				//user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setIsdCode(rs.getString("isd_code"));
				user.setMobile(rs.getString("mobile"));
				user.setTenant_id(rs.getString("tenant_id"));
				user.setTenant_name(rs.getString("tenant_name"));
		 }
			
		} catch (Exception ex) {
			user=null;
			ex.printStackTrace();
		} finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return user;
		
		
	}

}
