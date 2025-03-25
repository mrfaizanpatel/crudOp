
package com.cust.service;

import java.util.List;

import com.cust.Entity.Customer;

public interface Customerservice {

	List<Customer> fetchallcust();
	Customer fetchByID(Integer id);
	Customer CreateCust(Customer customer);
	Customer Updatecustomer(Customer customer);
	String deleteCustomer(Customer customer);
	void softDeleteCustomer(int id);
	void deletePermanently(int id);


}