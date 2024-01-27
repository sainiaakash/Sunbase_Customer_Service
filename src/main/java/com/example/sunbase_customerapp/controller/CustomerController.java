package com.example.sunbase_customerapp.controller;

import com.example.sunbase_customerapp.entity.Customer;
import com.example.sunbase_customerapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private List<Customer> getCustomersList(){
        List<Customer> list = customerService.getCustomersList();
        return list;
    }

    @GetMapping("searchByField")
    private  List<Customer> getCustomerListByField(@RequestParam("searchField") String searchField,
                                                   @RequestParam("fieldValue") String fieldValue){
        List<Customer> list = customerService.searchByField(searchField,fieldValue);
        return list;
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
