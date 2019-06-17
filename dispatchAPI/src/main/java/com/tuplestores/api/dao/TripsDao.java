package com.tuplestores.api.dao;

import java.util.List;

import com.tuplestores.api.model.general.Vehicle;



public interface TripsDao {
	List<Vehicle> getvehiclelist(String tenant_id);
}
