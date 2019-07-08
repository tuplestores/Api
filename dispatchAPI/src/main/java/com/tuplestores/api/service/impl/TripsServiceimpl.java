package com.tuplestores.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuplestores.api.dao.TripsDao;
import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.TripsModel;
import com.tuplestores.api.model.general.Vehicle;
import com.tuplestores.api.service.TripsService;


@Service("tripsService")
public class TripsServiceimpl implements TripsService{
	
	@Autowired
	TripsDao tripsDao;

	@Override
	public List<Vehicle> getvehiclelist(String tenant_id) {
		
		// TODO Auto-generated method stub
		return tripsDao.getvehiclelist(tenant_id);
		
	}
	//attach vehicle
	@Override 
	public Vehicle attachvehicle(String tenant_id,String driver_id,String vehicle_id) {
		return tripsDao.attachvehicle(tenant_id,driver_id,vehicle_id);
		
	}
	
	public ApiResponse updateLocation(String device_data) {
		return tripsDao.updateLocation(device_data);
    }
	@Override
	public ApiResponse acceptRideRequest(String tenant_id, String ride_request_id, String vehicle_id,
			String driver_id) {
		// TODO Auto-generated method stub
		return tripsDao.acceptRideRequest(tenant_id, ride_request_id, vehicle_id,driver_id);
	}
	@Override
	public ApiResponse declineRideRequest(String tenant_id, String ride_request_id, String vehicle_id,
			String driver_id) {
		// TODO Auto-generated method stub
		return tripsDao.acceptRideRequest(tenant_id, ride_request_id, vehicle_id,driver_id);
	}
	
	public List<TripsModel> getTrips(String tenant_id, String driver_id,String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return tripsDao.getTrips(tenant_id,driver_id,fromDate,toDate);
	}
	

}