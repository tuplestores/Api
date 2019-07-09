package com.tuplestores.api.service;

import java.util.List;

import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.TripsModel;
import com.tuplestores.api.model.general.Vehicle;

public interface TripsService {
	
		List<Vehicle> getvehiclelist(String tenant_id);
	
		//attach vehicle
		Vehicle attachvehicle(String tenant_id, String driver_id, String vehicle_id);
	
	
		//get trips
	
		List<TripsModel> getTrips(String tenant_id,String driver_id,String fromDate,String toDate);
	
		//update location
		ApiResponse updateLocation(String device_data);
		
		
		//accept ride request
		ApiResponse acceptRideRequest(String tenant_id, String ride_request_id, String vehicle_id, String driver_id);
		
		
		//decline ride request
		ApiResponse declineRideRequest(String tenant_id, String ride_request_id, String vehicle_id, String driver_id);

		
}
