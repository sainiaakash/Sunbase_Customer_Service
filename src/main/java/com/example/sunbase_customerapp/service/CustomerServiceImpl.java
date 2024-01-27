package com.example.sunbase_customerapp.service;

import com.example.sunbase_customerapp.entity.Customer;
import com.example.sunbase_customerapp.repository.CustomerRepository;
import com.example.sunbase_customerapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomerRecord(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public List<Customer> getCustomersList() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchByField(String fieldName, String fieldValue) {
        List<Customer> list;
        switch(fieldName){
            case "firstName": list = customerRepository.findByFirstNameContainingIgnoreCase(fieldValue);
                break;
            case "city": list = customerRepository.findByCityContainingIgnoreCase(fieldValue);
                break;
            case "email":list = customerRepository.findByEmailContainingIgnoreCase(fieldValue);
                break;
            case "phone":list = customerRepository.findByPhoneContainingIgnoreCase(fieldValue);
                break;
            default: list = null;
        }
        return list;
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void updateCustomer(Customer updatedCustomer) {
        customerRepository.save(updatedCustomer);
    }
}
