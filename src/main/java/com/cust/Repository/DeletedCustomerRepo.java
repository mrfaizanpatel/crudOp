package com.cust.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cust.Entity.DeletedCustomer;
@Repository
public interface DeletedCustomerRepo extends JpaRepository<DeletedCustomer, Integer>{

}
