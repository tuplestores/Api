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
import com.tuplestores.api.model.general.TripRequest;
import com.tuplestores.api.model.general.TripsModel;
import com.tuplestores.api.model.general.Vehicle;


@Repository("tripsDao")
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
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return vehicle;
		
	}
	
	
	//---------------GET TRIPS
		@Override	
		public List<TripsModel> getTrips(String tenant_id,String driver_id,String fromDate,String toDate) {
			
			List<TripsModel> tripList= null;
			
			String pickup_location_text = null;
			String dropoff_location_text = null;
			String cost = null;
			java.sql.CallableStatement callableStatement = null;
			Connection con = null;
			ResultSet rs = null;
			try {
				 tripList = new ArrayList<TripsModel>();
				 
				con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
				callableStatement = con.prepareCall("{call da_get_trips_p()}");
				callableStatement.setString(1, tenant_id);
				callableStatement.setString(2, driver_id);
				callableStatement.setString(3, fromDate);
				callableStatement.setString(4, toDate);

				rs = callableStatement.executeQuery();
				if(rs.next()) {
					TripsModel tm = new TripsModel();
					
					
					tm.setRide_request_id(rs.getNString("ride_request_id"));
					tm.setRequest_date_time(rs.getString("request_date_time"));
					tm.setProduct_id(rs.getString("product_id"));
					tm.setProduct_name(rs.getString("Product_name"));
					tm.setRider_id(rs.getString("rider_id"));
					tm.setRider_first_name(rs.getString("rider_first_name"));
					tm.setRider_last_name(rs.getString("rider_last_name"));
					tm.setPick_up_latitude(rs.getString("pick_up_latitude"));
					tm.setPick_up_longitude(rs.getString("pickup_longitude"));
					tm.setPick_up_location_text(rs.getString("pick_up_location_text"));
					tm.setDrop_off_latitude(rs.getString("drop_off_latitude"));
					tm.setDrop_off_longitude(rs.getString("drop_off_longitude"));
					tm.setDrop_off_location_text(rs.getString("drop_off_location_text"));
					tm.setAccept_date_time(rs.getString("accept_date_time"));
					tm.setPick_up_date_time(rs.getString("pick_up_date_time"));
					tm.setDrop_off_date_time(rs.getString("drop_off_date_time"));
					tripList.add(tm);
					tm = null;
				
				}
				
			}catch(Exception e){
			
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
			
			
			return tripList;
			
		}
		
		//update location
		
		@Override
		public ApiResponse updateLocation(String device_data) {
			java.sql.CallableStatement callableStatement = null;
			Connection con = null;
			ApiResponse api = null;
			String out = "E";
			
			try {
				api = new ApiResponse();
				con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
				callableStatement = con.prepareCall("{call da_load_device_data}");
				callableStatement.setString(1, device_data);
				callableStatement.registerOutParameter(2,java.sql.Types.VARCHAR);
				callableStatement.executeUpdate();
				out = callableStatement.getString(2);
				api.setStatus("Success");
				
				
				
			}catch(Exception e) {
				out = "E";
				api.setStatus("Failure");
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
		
		
		//---------------------Accept Ride Request
		@Override
		public ApiResponse acceptRideRequest(String tenant_id, String ride_request_id, String vehicle_id,String driver_id) {
			java.sql.CallableStatement callableStatement = null;
			Connection con = null;
			ApiResponse api = null;
			String out = "E";
			
			try {
				api = new ApiResponse();
				con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
				callableStatement = con.prepareCall("{call da_accept_ride_request}");
				callableStatement.setString(1, tenant_id);
				callableStatement.setString(2, ride_request_id);
				callableStatement.setString(3, vehicle_id);
				callableStatement.setString(4, driver_id);
				callableStatement.registerOutParameter(5,java.sql.Types.VARCHAR);
				callableStatement.executeUpdate();
				out = callableStatement.getNString(5) ;
				api.setStatus(out);
			}catch(Exception e) {
				api.setStatus("Failed");
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
	//decline ride request
		@Override
		public ApiResponse declineRideRequest(String tenant_id, String ride_request_id, String vehicle_id,
				String driver_id) {
			java.sql.CallableStatement callableStatement = null;
			Connection con = null;
			ApiResponse api = null;
			String out = "E";
			
			try {
				api = new ApiResponse();
				con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
				callableStatement = con.prepareCall("{call da_decline_ride_p}");
				callableStatement.setString(1, tenant_id);
				callableStatement.setString(2, ride_request_id);
				callableStatement.setString(3, vehicle_id);
				callableStatement.setString(4, driver_id);
				callableStatement.registerOutParameter(5,java.sql.Types.VARCHAR);
				callableStatement.executeUpdate();
				out = callableStatement.getNString(5) ;
				api.setStatus(out);
			}catch(Exception e) {
				api.setStatus("Failed");
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

		@Override
		public List<TripRequest> getRiderRequest(String tenant_id, String vehicle_id) {
			
				
				List<TripRequest> tripRequest= null;
				
		
				java.sql.CallableStatement callableStatement = null;
				Connection con = null;
				ResultSet rs = null;
				try {
					 tripRequest = new ArrayList<TripRequest>();
					 
					con = dispatchDBConnection.getJdbcTemplate().getDataSource().getConnection();
					callableStatement = con.prepareCall("{call da_get_new_ride_request_p()}");
					callableStatement.setString(1, tenant_id);
					callableStatement.setString(2, vehicle_id);
					

					rs = callableStatement.executeQuery();
					if(rs.next()) {
						TripRequest tm = new TripRequest();
						tm.setRide_request_id(rs.getString("ride_request_id"));
						tm.setProduct_id(rs.getString("product_id"));
						tm.setProduct_name(rs.getString("Product_name"));
						tm.setRider_id(rs.getString("rider_id"));
						tm.setRider_full_name(rs.getString("rider_full_name"));
						tm.setPick_up_latitude(rs.getString("pick_up_latitude"));
						tm.setPick_up_longitude(rs.getString("pickup_longitude"));
						tm.setPick_up_location_text(rs.getString("pick_up_location_text"));
						tm.setDrop_off_latitude(rs.getString("drop_off_latitude"));
						tm.setDrop_off_longitude(rs.getString("drop_off_longitude"));
						tm.setDrop_off_location_text(rs.getString("drop_off_location_text"));
						
						tripRequest.add(tm);
						tm = null;
					
					}
					
				}catch(Exception e){
				
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
				
				
				return tripRequest;
				
			}
		}


