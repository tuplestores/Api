package com.tuplestores.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("dispatchDBConnection")
public class DispatchDBConnection {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 @Autowired
	 public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	
	 }
	
	 public   JdbcTemplate getJdbcTemplate(){		
			return jdbcTemplate;		
	 }
		
     public  java.sql.Connection getNativeConnection(String driver, String url, 
    		  										  String username, String pwd) {
			
					String status = null;
					Connection	con = 
							null;
					
					try {
					Class.forName(driver).newInstance();
				
					con=DriverManager.getConnection(url, username, pwd);
				
			} catch (Exception e) {
				//logger.error( "******jdbcConnection Failure*******");
				//logger.error( "### Reason: {}, error code :{}",e.getMessage());
			}
		     return con;
		}
}
