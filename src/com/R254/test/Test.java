package com.R254.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forTest")
public class Test {

	@Autowired
	private DriverManagerDataSource dataSource;
	
	@RequestMapping("/dataSource")
	public void dataSource(){
		
		System.out.println(dataSource);
	}
}
