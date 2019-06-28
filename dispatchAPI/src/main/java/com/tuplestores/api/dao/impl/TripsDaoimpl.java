package com.tuplestores.api.dao.impl;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.mysql.cj.jdbc.CallableStatement;
import com.tuplestores.api.dao.DispatchDBConnection;
import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.Driver;
import com.tuplestores.api.model.general.Vehicle;


@Repository("TripsDao")
public class TripsDaoimpl implements com.tuplestores.api.dao.TripsDao{
	
	@Autowired
	DispatchDBConnection dispatchDBConnection;
	
	@Override
	public List<Vehicle> getvehiclelist(String tenant_id) {
		
		Vehicle vehicle = null;
		java.sql.CallableStatement callableStatement = null;
		Connection con = null;
		ResultSet rs = null;
		List<Vehicle> lstVehicle=null;
		
		try {
			con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
			callableStatement = con.prepareCall("{call ap_list_vehicle_p(?)}");
			
			callableStatement.setString(1,tenant_id);
			rs=callableStatement.executeQuery();
			lstVehicle = new ArrayList<Vehicle>();
			
			if(rs.next()) {
				vehicle =new Vehicle();
				vehicle.setTenant_id(rs.getString("Tenant_Id"));
				vehicle.setVehicle_id(rs.getString("Vehicle_id"));
				vehicle.setVehicle_name(rs.getString("Vehicle_name"));
				vehicle.setPlate_number(rs.getString("Plate_number"));
				lstVehicle.add(vehicle);
				
			}
			
		}catch(Exception e) {
			vehicle = null;
			e.printStackTrace();
		}
		
			return lstVehicle;
	}
	
	//----------attach vehicle------------------
	public Vehicle attachvehicle(String vehicle_id,String tenant_id,String driver_id) {
		Vehicle vehicle = null;
		Driver driver = null;
		java.sql.CallableStatement callableStatement = null;
		Connection con = null;
		String out = "E";
		ApiResponse api = null;
		ResultSet rs = null;
		try {
			con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
			callableStatement = con.prepareCall("{call da_driver_check_in_p}");
			
			callableStatement.setString(1,tenant_id);
			callableStatement.setString(2, vehicle_id);
			callableStatement.setString(3, driver_id);
			callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
			callableStatement.executeUpdate();
			out = callableStatement.getString(4);
			api.setStatus(out);
			
			api.setMsg("Success");
			
			
		}catch(Exception e) {
			out = "E";
			api.setStatus(out);
			api.setMsg("Failure");
			
			e.printStackTrace();
		}finally {
			if(con!=null)
				try {
					con.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
		}
		return vehicle;
		
	}

}
