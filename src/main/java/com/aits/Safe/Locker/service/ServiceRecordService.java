package com.aits.Safe.Locker.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.entity.ServiceRecord;
import com.aits.Safe.Locker.entity.ServiceRecord.Status;
import com.aits.Safe.Locker.entity.User;
import com.aits.Safe.Locker.entity.User.Role;
import com.aits.Safe.Locker.entity.Vehicle;
import com.aits.Safe.Locker.exception.InvalidServiceAdvisorException;
import com.aits.Safe.Locker.exception.ServiceRecordNotFoundException;
import com.aits.Safe.Locker.exception.UserNotFoundException;
import com.aits.Safe.Locker.exception.VehicleNotFoundException;
import com.aits.Safe.Locker.repo.ServiceRecordRepository;
import com.aits.Safe.Locker.repo.UserRepository;
import com.aits.Safe.Locker.repo.VehicleRepository;

@Service
public class ServiceRecordService {

    @Autowired
    private ServiceRecordRepository serviceRecordRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    public String addServiceRecord(ServiceRecord serviceRecord) {
        Vehicle vehicle = vehicleRepository.findById(serviceRecord.getVehicle().getId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with given ID is not found."));

        User user = userRepository.findById(serviceRecord.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException("User with given ID is not found."));

        if (user.getRole() != Role.SERVICE_ADVISOR) {
            throw new InvalidServiceAdvisorException("User is not a SERVICE_ADVISOR.");
        }

        serviceRecord.setVehicle(vehicle);
        serviceRecord.setUser(user);
        serviceRecord.setStatus(Status.PENDING);
        serviceRecordRepository.save(serviceRecord);

        return "Service record added successfully.";
    }

    public String updateServiceRecord(long id) {
        ServiceRecord serviceRecord = serviceRecordRepository.findById(id)
                .orElseThrow(() -> new ServiceRecordNotFoundException("Service record with ID " + id + " not found."));

        serviceRecord.setServiceDate(LocalDateTime.now());
        serviceRecord.setStatus(Status.COMPLETED);
        serviceRecordRepository.save(serviceRecord);

        return "Service record updated successfully.";
    }

    public String updateServiceRecord(ServiceRecord updatedRecord) {
        ServiceRecord existingRecord = serviceRecordRepository.findById(updatedRecord.getId())
                .orElseThrow(() -> new ServiceRecordNotFoundException("Service record with ID " + updatedRecord.getId() + " not found."));
        existingRecord.setServiceDate(updatedRecord.getServiceDate());
        existingRecord.setStatus(updatedRecord.getStatus());

        if (updatedRecord.getUser() != null) {
            User user = userRepository.findById(updatedRecord.getUser().getId())
                    .orElseThrow(() -> new UserNotFoundException("User not found."));
            if (user.getRole() != Role.SERVICE_ADVISOR) {
                throw new InvalidServiceAdvisorException("User is not a SERVICE_ADVISOR.");
            }
            existingRecord.setUser(user);
        }

        if (updatedRecord.getVehicle() != null) {
            Vehicle vehicle = vehicleRepository.findById(updatedRecord.getVehicle().getId())
                    .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found."));
            existingRecord.setVehicle(vehicle);
        }

        serviceRecordRepository.save(existingRecord);
        return "Service record updated successfully.";
    }

    public ServiceRecord getServiceRecordById(long id) {
        return serviceRecordRepository.findById(id)
                .orElseThrow(() -> new ServiceRecordNotFoundException("Service record with ID " + id + " not found."));
    }

    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordRepository.findAll();
    }

    public List<ServiceRecord> getServiceRecordsByStatus(Status status) {
        return serviceRecordRepository.findByStatus(status);
    }

    public List<ServiceRecord> getServiceRecordsOfUser(long userId) {
        return serviceRecordRepository.findByUserIdAndStatus(userId, Status.PENDING);
    }

    public String deleteServiceRecord(long id) {
        if (!serviceRecordRepository.existsById(id)) {
            throw new ServiceRecordNotFoundException("Service record with ID " + id + " does not exist.");
        }
        serviceRecordRepository.deleteById(id);
        return "Service record deleted successfully.";
    }
}
