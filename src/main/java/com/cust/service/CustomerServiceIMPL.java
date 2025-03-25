
package com.cust.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cust.Entity.Customer;
import com.cust.Repository.CustomerRepo;


@Service
public class CustomerServiceIMPL implements Customerservice{

	@Autowired
	private CustomerRepo custrepo;
	
	@Override
	public List<Customer> fetchallcust() {
		return (List<Customer>)custrepo.findAll();
	}

	@Override
	public Customer fetchByID(Integer id) {
		return custrepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
	}

	@Override
	public Customer CreateCust(Customer customer) {
		// TODO Auto-generated method stub
		return custrepo.save(customer);
	}

	@Override
	public Customer Updatecustomer(Customer customer) {
        return custrepo.save(customer);
    }

	@Override
	public String deleteCustomer(Customer customer) {
		 custrepo.delete(customer);
		 return "xustomer is deleted successfully for id :"+customer.getId();
	}

	@Service
	public class CustomerService {
	    public void softDeleteCustomer(int customerId) {
	    	custrepo.softDeleteCustomer(customerId);
	    }
	}


	@Override
	public void softDeleteCustomer(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public void deletePermanently(int customerId) {
		custrepo.deletePermanently(customerId);
    }

	

}

