package com.example.spring.batch.dao;

import com.example.spring.batch.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Long> {
}
