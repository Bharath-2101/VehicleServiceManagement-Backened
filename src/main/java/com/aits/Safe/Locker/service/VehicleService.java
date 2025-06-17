package com.aits.Safe.Locker.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.entity.Customer;
import com.aits.Safe.Locker.entity.Vehicle;
import com.aits.Safe.Locker.exception.CustomerNotFoundException;
import com.aits.Safe.Locker.exception.VehicleAlreadyExistsException;
import com.aits.Safe.Locker.exception.VehicleNotFoundException;
import com.aits.Safe.Locker.repo.CustomerRepository;
import com.aits.Safe.Locker.repo.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public String addVehicle(Vehicle vehicle) {
        Optional<Customer> customer = customerRepository.findById(vehicle.getCustomer().getId());
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found with given ID.");
        }

        if (!vehicleRepository.findByRegistrationNumber(vehicle.getRegistrationNumber()).isEmpty()) {
            throw new VehicleAlreadyExistsException("Vehicle registration number already exists.");
        }

        vehicle.setCustomer(customer.get());

        if (vehicle.getLastServiceDate() == null && vehicle.getNextServiceDate() == null) {
            vehicle.setLastServiceDate(LocalDate.now());
            vehicle.setNextServiceDate(LocalDate.now());
        }

        vehicleRepository.save(vehicle);
        return "Vehicle added successfully.";
    }

    public String updateVehicle(Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicle.getId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with given ID not found."));

        existingVehicle.setModel(vehicle.getModel());
        existingVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());
        existingVehicle.setLastServiceDate(vehicle.getLastServiceDate());
        existingVehicle.setNextServiceDate(vehicle.getNextServiceDate());
        existingVehicle.setYear(vehicle.getYear());
        existingVehicle.setFuel(vehicle.getFuel());
        existingVehicle.setType(vehicle.getType());

        if (vehicle.getCustomer() != null) {
            Customer customer = customerRepository.findById(vehicle.getCustomer().getId())
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found with given ID."));
            existingVehicle.setCustomer(customer);
        }

        vehicleRepository.save(existingVehicle);
        return "Vehicle updated successfully.";
    }

    public Vehicle getVehicleById(long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with given ID not found."));
    }

    public String deleteVehicle(long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new VehicleNotFoundException("Vehicle with given ID does not exist.");
        }
        vehicleRepository.deleteById(id);
        return "Vehicle deleted successfully.";
    }

    public String updateServiceDatesVehicle(long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with given ID not found."));
        vehicle.setLastServiceDate(LocalDate.now());
        vehicle.setNextServiceDate(LocalDate.now().plusDays(30));
        vehicleRepository.save(vehicle);
        return "Vehicle's service dates updated successfully.";
    }

    public List<Vehicle> getVehiclesDueThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate endOfWeek = today.plusDays(7);
        return vehicleRepository.findByNextServiceDateBetween(today, endOfWeek);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}
