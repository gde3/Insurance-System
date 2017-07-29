package com.R254.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.R254.entities.Cust_Insur;
import com.R254.entities.Customer;
import com.R254.entities.Insurance;

@Repository
public class CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Date orderNewInsurance(int custId, int insurId, int duration){
		
		Date cur = new Date();
		Date after = new Date();
		int addYear = (cur.getMonth() + duration) / 12;
		int afterMonth = (cur.getMonth() + duration) % 12;
		after.setMonth(afterMonth);
		after.setYear(addYear + cur.getYear());
		
		Customer customer = findSpecificCustomer(custId);
		Cust_Insur cust_Insur = new Cust_Insur();
		cust_Insur.setCustId(custId);
		cust_Insur.setInsurance(findSpecificInsurance(insurId));
		cust_Insur.setFromDate(cur);
		cust_Insur.setEndDate(after);
		customer.getInsurancesOrdered().add(cust_Insur);
		
		getSession().save(cust_Insur);
		getSession().saveOrUpdate(customer);
		
		return after;
	}
	
	public Insurance findSpecificInsurance(int insurId){
		
		String hql = "From Insurance i Where i.insuranceId = ?";
		return (Insurance)getSession().createQuery(hql).setInteger(0, insurId).list().get(0);
	}
	
	public Date extendInsuranceDate(int custId, int insurId, int duration){
		
		String hql = "Select c.endDate From Cust_Insur c Where c.custId = ? and c.insurance.insuranceId = ?";
		Date cur =  (Date) getSession().createQuery(hql).setInteger(0, custId).setInteger(1, insurId).list().get(0);
		
		int addYear = (cur.getMonth() + duration) / 12;
		int afterMonth = (cur.getMonth() + duration) % 12;
		cur.setMonth(afterMonth);
		cur.setYear(addYear + cur.getYear());
		
		hql = "Update Cust_Insur c Set c.endDate = ? Where c.custId = ? and c.insurance.insuranceId = ?";
		getSession().createQuery(hql).setDate(0, cur).setInteger(1, custId).setInteger(2, insurId).executeUpdate();
		
		return cur;
	}
	
	public Date checkInsuranceDate(int custId, int insurId){
		
		String hql = "Select c.endDate From Cust_Insur c Where c.custId = ? and c.insurance.insuranceId = ?";
		
		return (Date) getSession().createQuery(hql).setInteger(0, custId).setInteger(1, insurId).list().get(0);
	}
	
	public int checkWhetherOrdered(int custId, int insurId){
		
		String hql = "Select c.insurancesOrdered From Customer c Where c.customerId = ?";
		
		Query query = getSession().createQuery(hql).setInteger(0, custId);
		List<Cust_Insur> allInsurances = query.list();
		
		for(Cust_Insur insur : allInsurances){
			
			if(insur.getInsurance().getInsuranceId() == insurId) return 1;
		}
		
		return 0;
	}
	
	public Customer findSpecificCustomer(int custId){
		
		String hql = "From Customer c Where c.customerId = ?";
		return (Customer)getSession().createQuery(hql).setInteger(0, custId).list().get(0);
	}
	
	public void createQuery(Customer customer, String queryName, String desc){
		
		com.R254.entities.Query query = new com.R254.entities.Query();
		
		query.setCustomer(customer);
		query.setQueryDescription(desc);
		query.setQueryName(queryName);
		
		customer.getQueriesCreated().add(query);
		getSession().save(query);
	}
	
	public List<com.R254.entities.Query> getCountOfQuery(Integer id) {

		List<com.R254.entities.Query> queries = new ArrayList<>();

		String hql = "From Query q Where q.customer.customerId = ?";
		queries = getSession().createQuery(hql).setInteger(0, id).list();

		return queries;
	}

	public Customer checkPassword(String username, String password) {

		String hql = "From Customer c Where c.customerName = ? and c.customerPassword = ?";
		org.hibernate.Query query = getSession().createQuery(hql).setString(0, username).setString(1, password);
		if (query.list() == null || query.list().size() == 0)
			return null;

		return (Customer) query.list().get(0);
	}

	public String getAnswerWithId(int id) {

		String hql = "Select a.answerContent From Answer a Where a.answerId = ?";
		org.hibernate.Query query = getSession().createQuery(hql).setInteger(0, id);

		return (String) query.list().get(0);
	}

	public List<Insurance> getAllInsurances() {

		List<Insurance> insurances = new ArrayList<>();

		String hql = "From Insurance";
		insurances = getSession().createQuery(hql).list();

		return insurances;
	}

	public List<com.R254.entities.Query> getAllQuery() {

		List<com.R254.entities.Query> queries = new ArrayList<>();

		String hql = "From Query";
		queries = getSession().createQuery(hql).list();

		return queries;
	}
}
