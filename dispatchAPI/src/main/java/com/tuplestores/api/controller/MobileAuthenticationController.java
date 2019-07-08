package com.tuplestores.api.controller;

import java.util.LinkedHashMap;
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
import com.tuplestores.api.model.general.DriverModel;
import com.tuplestores.api.model.general.User;
import com.tuplestores.api.service.AuthenticationMobileService;
import com.tuplestores.api.service.AuthenticationService;

@Controller
public class MobileAuthenticationController {

	@Autowired
	AuthenticationMobileService authenticationMobileService;

	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/verifydriver", method = RequestMethod.GET)
	public @ResponseBody Object verifydriver(@RequestParam String isd,@RequestParam String Mobile,
			@RequestParam String invite,
			HttpSession session,
			HttpServletRequest request) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		HttpHeaders httpHeaders = new HttpHeaders();
		try {
			
			ApiResponse res=authenticationMobileService.verifydriver(isd,Mobile,invite);
			map.put("obj", res);	

		} catch (Exception e) {
			map.put("obj", null);
			e.printStackTrace();
		}		
				
		return new ResponseEntity<Map>(map,httpHeaders, HttpStatus.OK);	
	}
	
	
	//-------------get driver profile-----------------
		@RequestMapping(value = "/getDriverProfile" ,method = RequestMethod.GET)
		public @ResponseBody Object getDriverProfile(@RequestParam String driver_id,
													HttpSession session,
													HttpServletRequest request) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			HttpHeaders httpHeaders = new HttpHeaders();
			try {
				DriverModel listDriver = authenticationMobileService.getDriverProfile(driver_id);
				map.put("listDriver",listDriver);
			}catch(Exception e) {
				map.put("listDriver",null);
				e.printStackTrace();
			}
			return new ResponseEntity<Map>(map,httpHeaders,HttpStatus.OK);
		}
		
		//----------Update Driver Profile
		@RequestMapping(value = "/updateDriverProfile", method = RequestMethod.POST)
		public @ResponseBody Object acceptRideRequests(@RequestParam String driver_id,@RequestParam String email,
														@RequestParam String first_name,@RequestParam String last_name,
														@RequestParam String isd_code,@RequestParam String mobile,
														HttpSession session,
														HttpServletRequest request) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			HttpHeaders httpHeaders = new HttpHeaders();
			try {
				ApiResponse api=authenticationMobileService.updateDriverProfile(driver_id,email,first_name,last_name,isd_code,mobile);
				map.put("api",api);
			}catch(Exception e) {
				map.put("api",null);
				e.printStackTrace();
			}
			return new ResponseEntity<Map>(map,httpHeaders,HttpStatus.OK);
		}
		
	
	
}
