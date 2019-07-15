package com.tuplestores.api.dao;

import java.util.List;

import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.TripRequest;
import com.tuplestores.api.model.general.TripsModel;
import com.tuplestores.api.model.general.Vehicle;



public interface TripsDao {
	
	List<Vehicle> getvehiclelist(String tenant_id);
	
	ApiResponse attachvehicle(String tenant_id, String driver_id, String vehicle_id);
	
	List<TripsModel> getTrips(String tenant_id,String driver_id,String fromDate,String toDate);
	
	ApiResponse updateLocation(String device_data);
	
	ApiResponse acceptRideRequest(String tenant_id, String ride_request_id, String vehicle_id, String driver_id);
	
	ApiResponse declineRideRequest(String tenant_id, String ride_request_id, String vehicle_id);
	
	TripRequest getRiderRequest(String tenant_id, String vehicle_id);
	
    ApiResponse dettachVehicle(String tenant_id, String vehicle_id);
    
    ApiResponse cancelRideRequest(String tenant_id, String ride_request_id, String vehicle_id,String driver_id);
	
}
