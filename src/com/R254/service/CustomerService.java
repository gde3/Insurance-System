package com.R254.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.R254.DAO.CustomerDAO;
import com.R254.entities.Insurance;
import com.R254.entities.Query;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	public List<Insurance> getAllInsurances(){
		
		return customerDAO.getAllInsurances();
	}
	
	public List<Query> getAllQuery(){
		
		return customerDAO.getAllQuery();
	}
}
