package com.example.sunbase_customerapp.repository;

import com.example.sunbase_customerapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);
    List<Customer> findByCityContainingIgnoreCase(String city);
    List<Customer> findByPhoneContainingIgnoreCase(String phone);
    List<Customer> findByEmailContainingIgnoreCase(String email);
}
