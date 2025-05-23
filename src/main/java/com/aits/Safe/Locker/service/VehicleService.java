package com.aits.Safe.Locker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.DTO.VehicleDTO;
import com.aits.Safe.Locker.entity.Customer;
import com.aits.Safe.Locker.entity.Vehicle;
import com.aits.Safe.Locker.repo.CustomerRepository;
import com.aits.Safe.Locker.repo.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	public String addVehicle(Vehicle vehicle)
	{
		Optional<Customer> customer=customerRepository.findById(vehicle.getCustomer().getId());
		if(customer.isEmpty())
		{
			return "Customer not found with given id";
		}
		
		if(!vehicleRepository.findByRegistrationNumber(vehicle.getRegistrationNumber()).isEmpty())
		{
			return "Vehicle Registration Number is already exists";
		}
		
		vehicle.setCustomer(customer.get());
		if(vehicle.getLastServiceDate()==null && vehicle.getNextServiceDate()==null) {
		vehicle.setLastServiceDate(LocalDate.now());
		vehicle.setNextServiceDate(LocalDate.now());
		}
		
		vehicleRepository.save(vehicle);
		return "Vehicle is added";
	}
	
	public String updateVehicle(long id)
	{
		Vehicle vehicle=vehicleRepository.findById(id).get();
		vehicle.setLastServiceDate(LocalDate.now());
		vehicle.setNextServiceDate(LocalDate.now().plusDays(30));
		vehicleRepository.save(vehicle);
		return "Vehicle's Service dates are updated";
	}
	
	public List<VehicleDTO> getVehiclesDueThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate endOfWeek = today.plusDays(7);
        List<Vehicle> dueVehicles= vehicleRepository.findByNextServiceDateBetween(today, endOfWeek);
        
        List<VehicleDTO> modifiedDueVehicles=new ArrayList<>();
        
        for(Vehicle vehicle:dueVehicles)
        {
        	VehicleDTO modifiedDueVehicle=new VehicleDTO();
        	modifiedDueVehicle.setModel(vehicle.getModel());
        	modifiedDueVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());
        	modifiedDueVehicle.setYear(vehicle.getYear());
        	modifiedDueVehicle.setFuel(vehicle.getFuel());
        	modifiedDueVehicle.setType(vehicle.getType());
        	modifiedDueVehicle.setLastServiceDate(vehicle.getLastServiceDate());
        	modifiedDueVehicle.setNextServiceDate(vehicle.getNextServiceDate());
        	modifiedDueVehicles.add(modifiedDueVehicle);
        }
        return modifiedDueVehicles;
    }

}
