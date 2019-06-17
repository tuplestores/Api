package com.tuplestores.api.service;

import java.util.List;

import com.tuplestores.api.model.general.Vehicle;

public interface TripsService {
	
	List<Vehicle> getvehiclelist(String tenant_id);
}
