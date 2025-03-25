package com.cust.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cust.Entity.Customer;

import jakarta.transaction.Transactional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	 @Transactional
	    @Modifying
	    @Query(value = "INSERT INTO DeletedCustomer (id, name, email, phone, created_at) " +
	                   "SELECT id, name, email, phone, created_at FROM Customer WHERE id = :customerId; " +
	                   "DELETE FROM Customer WHERE id = :customerId", 
	                   nativeQuery = true)
	    void softDeleteCustomer(@Param("customerId") int customerId);

	 @Transactional
	    @Modifying
	    @Query(value = "DELETE FROM DeletedCustomer WHERE id = :customerId", nativeQuery = true)
	    void deletePermanently(@Param("customerId") int customerId);
}


