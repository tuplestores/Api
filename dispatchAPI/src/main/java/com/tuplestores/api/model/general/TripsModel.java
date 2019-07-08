package com.tuplestores.api.model.general;

public class TripsModel {
	
	private String pick_up_location_text;
	private String drop_off_location_text;
	private String vehicle_name;
	private String cost;
	public String getPick_up_location_text() {
		return pick_up_location_text;
	}
	public void setPick_up_location_text(String pick_up_location_text) {
		this.pick_up_location_text = pick_up_location_text;
	}
	public String getDrop_off_location_text() {
		return drop_off_location_text;
	}
	public void setDrop_off_location_text(String drop_off_location_text) {
		this.drop_off_location_text = drop_off_location_text;
	}
	public String getVehicle_name() {
		return vehicle_name;
	}
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	

}
