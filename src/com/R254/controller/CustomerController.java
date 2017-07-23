package com.R254.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.R254.service.CustomerService;

@Controller
@RequestMapping(value="customerOpe")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="getAllInsurances")
	public String listAllInsurances(Map<String, Object> map){
		
		map.put("insurances", customerService.getAllInsurances());
		return "insurances";
	}
}
