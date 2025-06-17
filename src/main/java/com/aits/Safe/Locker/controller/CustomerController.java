package com.aits.Safe.Locker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aits.Safe.Locker.entity.Customer;
import com.aits.Safe.Locker.exception.NullValueException;
import com.aits.Safe.Locker.service.CustomerService;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/admin/addCustomer")
    public String addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/admin/allCustomers")
    public List<Customer> allCustomers() {
        return customerService.getAllCustomers();
    }


    @GetMapping("/admin/getCustomer")
    public Customer getCustomer(@RequestParam Long id) {
        if (id == null) {
            throw new NullValueException("Customer ID is null");
        }
        return customerService.getCustomerById(id);
    }


    @PutMapping("/admin/updateCustomer")
    public String updateCustomer(@RequestBody Customer customer) {
        if (customer.getId() == null) {
            throw new NullValueException("Customer ID is null");
        }
        return customerService.updateCustomer(customer);
    }

 
    @DeleteMapping("/admin/deleteCustomer")
    public String deleteCustomer(@RequestParam Long id) {
        if (id == null) {
            throw new NullValueException("Customer ID is null");
        }
        return customerService.deleteCustomer(id);
    }
}
