package com.R254.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.R254.DAO.CustomerDAO;
import com.R254.entities.Customer;
import com.R254.entities.Insurance;
import com.R254.entities.Query;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	public Date orderNewInsurance(int custId, int insurId, int duration){
		
		return customerDAO.orderNewInsurance(custId, insurId, duration);
	}
	
	public Date extendInsuranceDate(int custId, int insurId, int duration){
		
		return customerDAO.extendInsuranceDate(custId, insurId, duration);
	}
	
	public Date checkInsuranceDate(int custId, int insurId){
		
		return customerDAO.checkInsuranceDate(custId, insurId);
	}
	
	public int checkWhetherOrdered(int custId, int insurId){
		
		return customerDAO.checkWhetherOrdered(custId, insurId);
	}
	
	public Customer configPersonalPage(int custId){
		
		Customer customer = customerDAO.findSpecificCustomer(custId);
		return customer;
	}
	
	public void createQuery(int custId, String queryName, String desc){
		
		Customer customer = customerDAO.findSpecificCustomer(custId);
		customerDAO.createQuery(customer, queryName, desc);
	}
	
	public List<Query> getCountOfQuery(Integer id){
		
		return customerDAO.getCountOfQuery(id);
	}
	
	public Customer checkPassword(String username, String password){
		
		return customerDAO.checkPassword(username, password);
	}
	
	public List<Insurance> getAllInsurances(){
		
		return customerDAO.getAllInsurances();
	}
	
	public List<Query> getAllQuery(){
		
		return customerDAO.getAllQuery();
	}
	
	public String getAnswerWithId(int id){
		
		return customerDAO.getAnswerWithId(id);
	}
}
