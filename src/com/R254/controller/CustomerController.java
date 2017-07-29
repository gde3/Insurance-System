package com.R254.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.R254.entities.Customer;
import com.R254.service.CustomerService;

@Controller
@RequestMapping(value="customerOpe")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="orderNewInsurance")
	@ResponseBody
	public String orderNewInsurance(@RequestParam int custId, @RequestParam int insurId, @RequestParam int duration){
		
		return customerService.orderNewInsurance(custId, insurId, duration) + "";
	}
	
	@RequestMapping(value="extendInsuranceDate")
	@ResponseBody
	public String extendInsuranceDate(@RequestParam int custId, @RequestParam int insurId, @RequestParam int duration){
		
		return customerService.extendInsuranceDate(custId, insurId, duration) + "";
	}
	
	@RequestMapping(value="checkInsuranceDate")
	@ResponseBody
	public String checkInsuranceDate(@RequestParam int custId, @RequestParam int insurId){
		
		return customerService.checkInsuranceDate(custId, insurId) + "";
	}
	
	@RequestMapping(value="getQueryAnswer")
	@ResponseBody
	public String getAnswer(@RequestParam int id){
		
		return customerService.getAnswerWithId(id);
	}
	
	@RequestMapping(value="checkWhetherOrdered")
	@ResponseBody
	public String checkWhetherOrdered(@RequestParam int custId, @RequestParam int insurId){
		
		System.out.println(custId);
		System.out.println(insurId);
		System.out.println(customerService.checkWhetherOrdered(custId, insurId));
		return customerService.checkWhetherOrdered(custId, insurId) + "";
	}
	
	@RequestMapping(value="createQuery")
	public String createQuery(@RequestParam int custId, @RequestParam String queryName, @RequestParam String desc){
		
		customerService.createQuery(custId, queryName, desc);
		return "forward:/insurancepro/customerOpe/accessForum";
	}
	
	@RequestMapping(value="getCountOfQuery")
	public String getCountOfQuery(HttpSession httpSession){
		
		httpSession.setAttribute("queries", customerService.getCountOfQuery((Integer)httpSession.getAttribute("custId")));
		return "createQuery";
	}
	
	@RequestMapping(value="customerLogin")
	public String customerLogin(@RequestParam String username, @RequestParam String password, HttpSession httpSession){
		
		Customer customer = customerService.checkPassword(username, password);
		if(customer == null){
			
			httpSession.setAttribute("errorMessage", "Either username or password is wrong!!!");
			return "error";
		}
		
		httpSession.setAttribute("custId", customer.getCustomerId());
		return "forward:/insurancepro/customerOpe/getPersonalInformation";
	}
	
	@RequestMapping(value="getPersonalInformation")
	public String getPersonalInformation(Map<String, Object> map, HttpSession httpSession){
		
		Customer customer = customerService.configPersonalPage((Integer)(httpSession.getAttribute("custId")));
		httpSession.setAttribute("customer", customer);
		return "personalInformation";
	}
	
	@RequestMapping(value="getAllInsurances")
	public String listAllInsurances(Map<String, List> map){
		
		map.put("insurances", customerService.getAllInsurances());
		return "insurances";
	}
	
	@RequestMapping(value="accessForum")
	public String accessForum(Map<String, Object> map){
		
		map.put("queries", customerService.getAllQuery());
		return "forum";
	}	
}
