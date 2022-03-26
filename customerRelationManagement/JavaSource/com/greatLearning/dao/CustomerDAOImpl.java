package com.greatLearning.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.greatLearning.model.Customer;

@Repository
@Transactional
@EnableTransactionManagement

public class CustomerDAOImpl implements CustomerDAO {
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		Session currentSession = sessionFactory.getCurrentSession();
				
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",
											Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
				
	return customers;
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("rawtypes")
		Query theQuery = 
				currentSession.createQuery("delete from Customer where customerid=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();		
	}

}

