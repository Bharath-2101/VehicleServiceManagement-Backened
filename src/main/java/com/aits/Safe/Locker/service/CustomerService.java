package com.aits.Safe.Locker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.entity.Customer;
import com.aits.Safe.Locker.repo.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	public String addCustomer(Customer customer)
	{
		List<Customer> existingCustomer=customerRepository.findByEmailOrMobile(customer.getEmail(), customer.getMobile());
		if(!existingCustomer.isEmpty())
		{
			return "Email or Mobile number already exists";
		}
		customerRepository.save(customer);
		return "Customer added Successfully";
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

}