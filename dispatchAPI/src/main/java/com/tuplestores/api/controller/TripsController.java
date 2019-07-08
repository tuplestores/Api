package com.tuplestores.api.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.TripsModel;
import com.tuplestores.api.model.general.Vehicle;
import com.tuplestores.api.service.TripsService;

@Controller
public class TripsController {

	@Autowired
	TripsService tripsService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getvehiclelist" ,method = RequestMethod.GET)
	public @ResponseBody Object vehiclelist(@RequestParam String tenant_id,@RequestParam String vehicle_name,
			@RequestParam String vehicle_id,@RequestParam String plate_number,
											HttpSession session,
											HttpServletRequest request) {
		
	
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		HttpHeaders httpHeaders = new HttpHeaders();
		try {
			List<Vehicle> lstVehicle = tripsService.getvehiclelist(tenant_id);
			map.put("lstVehicle", lstVehicle);
		}catch(Exception e) {
			map.put("vehicle",null);
			e.printStackTrace();
		}
		return new ResponseEntity<Map>(map,httpHeaders,HttpStatus.OK);
	}
	
	
	//----------------attach vehicle-----------------------
	@RequestMapping(value = "/attachvehicle" , method = RequestMethod.POST)
	public @ResponseBody Object attachvehicle(@RequestParam String vehicle_id,@RequestParam String driver_id,@RequestParam String tenant_id,@RequestParam String status,
											  HttpSession session,
											  
											  HttpServletRequest request) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		HttpHeaders httpHeaders = new HttpHeaders();
		try {
			Vehicle vehicle=tripsService.attachvehicle(tenant_id,driver_id,vehicle_id);
			map.put("vehicle", vehicle);		
		} catch (Exception e) {
			map.put("vehicle", null);
			e.printStackTrace();
		}		
		return new ResponseEntity<Map>(map,httpHeaders,HttpStatus.OK);
		
		}
	
	//---------------GET TRIPS-----------------------------------
	@RequestMapping(value = "/getTrips" , method = RequestMethod.POST)
	public @ResponseBody Object  getTrips(@RequestParam String tenant_id,@RequestParam String driver_id,
											@RequestParam String fromDate,
											@RequestParam String toDate,
											HttpSession session,
											HttpServletRequest request) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		HttpHeaders httpHeaders = new HttpHeaders();
		try {
			List<TripsModel> tripList=tripsService.getTrips(tenant_id, driver_id, fromDate, toDate);
			map.put("trips", tripList);
		}catch(Exception e) {
			map.put("trips",null);
			e.printStackTrace();
		}
		return new ResponseEntity<Map>(map,httpHeaders,HttpStatus.OK);
	}
	
	
	//-----------------SEND LOCATIONS---------------------------------
		@RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
		public @ResponseBody Object sendLocations(@RequestParam String device_data,
												  HttpSession session,
												  HttpServletRequest request) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			HttpHeaders httpHeaders = new HttpHeaders();
			try {
				ApiResponse api=tripsService.updateLocation(device_data);
				map.put("device_data", device_data);
			}catch(Exception e) {
				map.put("device_data",null);
				e.printStackTrace();
			}
			return new ResponseEntity<Map>(map,httpHeaders,HttpStatus.OK);
		}
		
		//------------Accept Ride Requests-----------------------------
		@RequestMapping(value = "/acceptRideRequests", method = RequestMethod.POST)
		public @ResponseBody Object acceptRideRequests(@RequestParam String tenant_id,@RequestParam String ride_request_id,
														@RequestParam String vehicle_id,@RequestParam String driver_id,
														HttpSession session,
														HttpServletRequest request) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			HttpHeaders httpHeaders = new HttpHeaders();
			try {
				ApiResponse api=tripsService.acceptRideRequest(tenant_id,ride_request_id,vehicle_id,driver_id);
				map.put("api",api);
			}catch(Exception e) {
				map.put("api",null);
				e.printStackTrace();
			}
			return new ResponseEntity<Map>(map,httpHeaders,HttpStatus.OK);
		}
		
		//--------------Decline Ride Requests
		@RequestMapping(value = "/declineRideRequests", method = RequestMethod.POST)
		public @ResponseBody Object declineRideRequests(@RequestParam String tenant_id,@RequestParam String ride_request_id,
														@RequestParam String vehicle_id,@RequestParam String driver_id,
														HttpSession session,
														HttpServletRequest request) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			HttpHeaders httpHeaders = new HttpHeaders();
			try {
				ApiResponse api=tripsService.declineRideRequest(tenant_id,ride_request_id,vehicle_id,driver_id);
				map.put("api",api);
			}catch(Exception e) {
				map.put("api",null);
				e.printStackTrace();
			}
			return new ResponseEntity<Map>(map,httpHeaders,HttpStatus.OK);
		}
	
	
}//Class


