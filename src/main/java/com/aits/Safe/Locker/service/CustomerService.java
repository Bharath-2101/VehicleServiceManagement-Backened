package com.aits.Safe.Locker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.entity.Customer;
import com.aits.Safe.Locker.exception.CustomerAlreadyExistsException;
import com.aits.Safe.Locker.exception.CustomerNotFoundException;
import com.aits.Safe.Locker.repo.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String addCustomer(Customer customer) {
        List<Customer> existingCustomer = customerRepository.findByEmailOrMobile(customer.getEmail(), customer.getMobile());
        if (!existingCustomer.isEmpty()) {
            throw new CustomerAlreadyExistsException("Customer email or mobile number already exists.");
        }
        customerRepository.save(customer);
        return "Customer added successfully.";
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));
    }

    public String updateCustomer(Customer customer) {
        Customer existingCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + customer.getId() + " not found."));

        existingCustomer.setName(customer.getName());
        existingCustomer.setMobile(customer.getMobile());
        existingCustomer.setEmail(customer.getEmail());

        customerRepository.save(existingCustomer);
        return "Customer updated successfully.";
    }

    public String deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " does not exist.");
        }
        customerRepository.deleteById(id);
        return "Customer deleted successfully.";
    }
}
