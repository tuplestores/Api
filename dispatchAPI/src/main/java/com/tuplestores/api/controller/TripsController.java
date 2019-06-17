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
	
}//Class


