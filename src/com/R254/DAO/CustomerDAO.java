package com.R254.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.R254.entities.Insurance;
import com.R254.entities.Query;

@Repository
public class CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){ return this.sessionFactory.getCurrentSession(); }
	
	public List<Insurance> getAllInsurances(){
		
		List<Insurance> insurances = new ArrayList<>();
		
		String hql = "From Insurance";
		insurances = getSession().createQuery(hql).list();
		
		return insurances;
	}
	
	public List<Query> getAllQuery(){
		
		List<Query> queries = new ArrayList<>();
		
		String hql = "From Query";
		queries = getSession().createQuery(hql).list();
		
		return queries;
	}
}
