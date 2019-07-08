package com.tuplestores.api.model.general;

public class Vehicle {
private String tenant_id;
private String vehicle_id;
private String vehicle_name;
private String plate_number;
private String pickup_location_text;
private String dropoff_location_text;
private String cost;
public String getTenant_id() {
	return tenant_id;
}
public void setTenant_id(String tenant_id) {
	this.tenant_id = tenant_id;
}
public String getVehicle_id() {
	return vehicle_id;
}
public void setVehicle_id(String vehicle_id) {
	this.vehicle_id = vehicle_id;
}
public String getVehicle_name() {
	return vehicle_name;
}
public void setVehicle_name(String vehicle_name) {
	this.vehicle_name = vehicle_name;
}
public String getPickup_location_text() {
	return pickup_location_text;
}
public void setPickup_location_text(String pickup_location_text) {
	this.pickup_location_text = pickup_location_text;
}
public String getDropoff_location_text() {
	return dropoff_location_text;
}
public void setDropoff_location_text(String dropoff_location_text) {
	this.dropoff_location_text = dropoff_location_text;
}
public String getCost() {
	return cost;
}
public void setCost(String cost) {
	this.cost = cost;
}
public String getPlate_number() {
	return plate_number;
}
public void setPlate_number(String plate_number) {
	this.plate_number = plate_number;
}
}