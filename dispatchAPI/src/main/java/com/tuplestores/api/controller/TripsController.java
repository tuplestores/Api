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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuplestores.api.model.general.ApiResponse;
import com.tuplestores.api.model.general.TripRequest;
import com.tuplestores.api.model.general.TripsModel;
import com.tuplestores.api.model.general.Vehicle;
import com.tuplestores.api.service.TripsService;

@Controller
public class TripsController {

	@Autowired
	TripsService tripsService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getvehiclelist" ,method = RequestMethod.GET)
	public @ResponseBody Object vehiclelist( @RequestParam String tenant_id ){
		
	
		//Map<String, Object> map = new LinkedHashMap<String, Object>();
		HttpHeaders httpHeaders = new HttpHeaders();
		List<Vehicle> lstVehicle=null;
		try {
		    lstVehicle = tripsService.getvehiclelist(tenant_id);
			//map.put("lstVehicle", lstVehicle);
		}catch(Exception e) {
			//map.put("lstVehicle",null);
			
			e.printStackTrace();
		}
		return new ResponseEntity<List<Vehicle>>(lstVehicle,httpHeaders,HttpStatus.OK);
	}
	
	
	//----------------attach vehicle-----------------------
	@RequestMapping(value = "/attachvehicle" , method = RequestMethod.GET)
	public @ResponseBody Object attachvehicle( @RequestParam String vehicle_id,@RequestParam String driver_id,
											   @RequestParam String tenant_id) {
		
		
		HttpHeaders httpHeaders = new HttpHeaders();
		ApiResponse api=null;
		
		try {
		    api=tripsService.attachvehicle(tenant_id,driver_id,vehicle_id);
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		return new ResponseEntity<ApiResponse>(api,httpHeaders,HttpStatus.OK);
		
		}
	
	//---------------GET TRIPS-----------------------------------
	@RequestMapping(value = "/getTrips" , method = RequestMethod.GET)
	public @ResponseBody Object  getTrips(@RequestParam String tenant_id,
											@RequestParam String driver_id,
											@RequestParam String fromDate,
											@RequestParam String toDate) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		List<TripsModel> tripList=null;
		
		try {
	        tripList=tripsService.getTrips(tenant_id, driver_id, fromDate, toDate);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<List<TripsModel>>(tripList,httpHeaders,HttpStatus.OK);
	}
	
	
	//-----------------SEND LOCATIONS---------------------------------
		@RequestMapping(value = "/loadDeviceData", method = RequestMethod.POST)
		public @ResponseBody Object loadDeviceData(@RequestBody String device_data ){

			HttpHeaders httpHeaders = new HttpHeaders();
			ApiResponse api=null;
			try {
				 api=tripsService.updateLocation(device_data);
			
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			return new ResponseEntity<ApiResponse>(api,httpHeaders,HttpStatus.OK);
		}
		
		//------------Accept Ride Requests-----------------------------
		@RequestMapping(value = "/acceptRideRequests", method = RequestMethod.GET)
		public @ResponseBody Object acceptRideRequests(@RequestParam String tenant_id,
														@RequestParam String ride_request_id,
														@RequestParam String vehicle_id,
														@RequestParam String driver_id) {
		
			HttpHeaders httpHeaders = new HttpHeaders();
			ApiResponse api=null;
			try {
				 api=tripsService.acceptRideRequest(tenant_id,ride_request_id,vehicle_id,driver_id);
				
			}catch(Exception e) {
				 
				e.printStackTrace();
			}
			return new ResponseEntity<ApiResponse>(api,httpHeaders,HttpStatus.OK);
		}
		
		//--------------Decline Ride Requests
		@RequestMapping(value = "/declineRideRequests", method = RequestMethod.GET)
		public @ResponseBody Object declineRideRequests(@RequestParam String tenant_id,@RequestParam String ride_request_id,
														@RequestParam String vehicle_id) {
			
			
			HttpHeaders httpHeaders = new HttpHeaders();
			ApiResponse api=null;
			try {
			    api=tripsService.declineRideRequest(tenant_id,ride_request_id,vehicle_id);
				
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			return new ResponseEntity<ApiResponse>(api,httpHeaders,HttpStatus.OK);
		}
		
		//--------get ride request---------------
		
		@RequestMapping(value = "/getRiderRequest" , method = RequestMethod.GET)
		public @ResponseBody Object  getRiderRequest(@RequestParam String tenant_id,@RequestParam String vehicle_id) {
			
			HttpHeaders httpHeaders = new HttpHeaders();
			TripRequest triprequest=null;
			try {
				triprequest=tripsService.getRiderRequest(tenant_id, vehicle_id);
				
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			return new ResponseEntity<TripRequest>(triprequest,httpHeaders,HttpStatus.OK);
		}
		
		
		//----------------dettach vehicle-----------------------
		@RequestMapping(value = "/dettachVehicle" , method = RequestMethod.GET)
		public @ResponseBody Object dettachVehicle( @RequestParam String tenant_id,@RequestParam String driver_id) {
			
			
			HttpHeaders httpHeaders = new HttpHeaders();
			ApiResponse api=null;
			
			try {
			    api=tripsService.dettachVehicle(tenant_id, driver_id);
					
			} catch (Exception e) {
				
				e.printStackTrace();
			}		
			return new ResponseEntity<ApiResponse>(api,httpHeaders,HttpStatus.OK);
			
			}
	
	
}//Class


