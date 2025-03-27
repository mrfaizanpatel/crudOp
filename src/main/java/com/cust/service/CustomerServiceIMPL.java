
package com.cust.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cust.Entity.Customer;
import com.cust.Entity.DeletedCustomer;
import com.cust.Repository.CustomerRepo;
import com.cust.Repository.DeletedCustomerRepo;


@Service
public class CustomerServiceIMPL implements Customerservice{

	@Autowired
	private CustomerRepo custrepo;
	
	@Autowired
	private DeletedCustomerRepo deletedcustrepo;
	
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
		 return "Customer is deleted successfully for id :"+customer.getId();
	}

	/*@Service
	public class CustomerService {
	    public void softDeleteCustomer(int customerId) {
	    	custrepo.softDeleteCustomer(customerId);
	    }
	}*/


	@Override
	public void softDeleteCustomer(int id) {
	     Customer customer=custrepo.findById(id).get();
	     DeletedCustomer deletedCustomer = new DeletedCustomer();
//	     deletedCustomer.setId(customer.getId());
	     deletedCustomer.setName(customer.getName());
	     deletedCustomer.setEmail(customer.getEmail());
	     deletedCustomer.setPassword(customer.getPassword());
	     deletedcustrepo.save(deletedCustomer);
		custrepo.deleteById(id);
	}
	
	public void deletePermanently(int id) {
    deletedcustrepo.deleteById(id);
	}

	@Override
	public void restoreCustomer(int id) {
     DeletedCustomer deletecustomer=deletedcustrepo.findById(id).get();
     Customer customer2=new Customer();
     customer2.setName(deletecustomer.getName());
     customer2.setEmail(deletecustomer.getEmail());
     customer2.setPassword(deletecustomer.getPassword());
     custrepo.save(customer2);
     deletedcustrepo.deleteById(id);
		
	}

	@Override
	public List<DeletedCustomer> fetchdeletedcust() {
		// TODO Auto-generated method stub
		return (List<DeletedCustomer>)deletedcustrepo.findAll();
	}

	

}

