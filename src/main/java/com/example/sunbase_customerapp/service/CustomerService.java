package com.example.sunbase_customerapp.service;

import com.example.sunbase_customerapp.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomerRecord(Customer customer);
    List<Customer> getCustomersList();
    List<Customer> searchByField(String fieldName, String fieldValue);
    void deleteCustomerById(Long customerId);
}
