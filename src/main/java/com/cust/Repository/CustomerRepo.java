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

	}


