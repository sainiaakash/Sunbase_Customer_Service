package com.example.sunbase_customerapp.controller;

import com.example.sunbase_customerapp.model.Customer;
import com.example.sunbase_customerapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("saveCustomer")
    public void saveCustomerRecord(@RequestBody Customer customer){
        customerService.saveCustomerRecord(customer);
    }

    @GetMapping("customerList")
    private ResponseEntity<List<Customer>> getCustomersList(){
        List<Customer> customersList = customerService.getCustomersList();
        if(customersList.isEmpty()){
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(customersList, HttpStatus.OK);
        }
    }

    @GetMapping("searchByField")
    private ResponseEntity<List<Customer>> getCustomerListByField(@RequestParam("searchField") String searchField,
                                                                 @RequestParam("fieldValue") String fieldValue){
        List<Customer> customersList = customerService.searchByField(searchField,fieldValue);
        if(customersList.isEmpty()){
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(customersList, HttpStatus.OK);
        }
    }

    @DeleteMapping("delete/{customerId}")
    private void deleteCustomerById(@PathVariable Long customerId){
        customerService.deleteCustomerById(customerId);
    }

    @PutMapping("update")
    private void updateCustomer(@RequestBody Customer updatedCustomer){
        customerService.updateCustomer(updatedCustomer);
    }

}
