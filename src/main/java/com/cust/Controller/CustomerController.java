package com.cust.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cust.Entity.Customer;
import com.cust.Entity.DeletedCustomer;
import com.cust.Repository.CustomerRepo;
import com.cust.service.Customerservice;

import ch.qos.logback.core.status.Status;


@RestController
public class CustomerController {
@Autowired
private Customerservice customerservice;
@Autowired
private CustomerRepo cr;
@CrossOrigin(origins =   "http://localhost:4200")

@GetMapping ("/api/customer")
public ResponseEntity<List<Customer>> fetchAll() {
    System.out.println("Fetching all customers...");
    return ResponseEntity.ok(customerservice.fetchallcust());
}
@GetMapping("/api/deleted/customer")
public ResponseEntity<List<DeletedCustomer>>fetchdeletedcust(){
	return ResponseEntity.ok(customerservice.fetchdeletedcust());
}

   @GetMapping("/api/customer/{id}")
   public ResponseEntity<Customer> fetcByID(@PathVariable("id")Integer id){
	   return ResponseEntity.ok(customerservice.fetchByID(id));
   }
   @PostMapping("/api/customer")
   public ResponseEntity<Customer> createcustomer(@RequestBody Customer customer){
	   return ResponseEntity.ok(customerservice.CreateCust(customer));
   }
  
   @PutMapping("/api/customer/{id}")
   public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer updatedCustomer) {
       Customer customer = customerservice.fetchByID(id);
       if (customer == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
       customer.setName(updatedCustomer.getName());
       customer.setEmail(updatedCustomer.getEmail());
       customer.setPassword(updatedCustomer.getPassword());

       Customer updated = customerservice.Updatecustomer(customer);
       return ResponseEntity.ok(updated);
   }
   @DeleteMapping("/api/customer/softdelete/{id}")
   public ResponseEntity<Customer>softdelete(@PathVariable ("id") Integer id){
      
	   customerservice.softDeleteCustomer(id);
	   return new	 ResponseEntity<Customer>(HttpStatus.OK);  
   }
   @DeleteMapping("/api/customer/restore/{id}")
   public ResponseEntity<DeletedCustomer>restoredata(@PathVariable ("id") int id){
	   customerservice.restoreCustomer(id);
	   return new ResponseEntity<DeletedCustomer>(HttpStatus.OK);
   }
   
  
   @DeleteMapping("/api/customer/{id}")
   public ResponseEntity<DeletedCustomer> deleteCustomer(@PathVariable("id") Integer id) {
       customerservice.deletePermanently(id);
       return new ResponseEntity<DeletedCustomer>(HttpStatus.OK);
   }
   
   

}
