package com.tuplestores.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuplestores.api.dao.TripsDao;
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

}