package com.aits.Safe.Locker.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.entity.ServiceRecord;
import com.aits.Safe.Locker.entity.ServiceRecord.Status;
import com.aits.Safe.Locker.entity.User;
import com.aits.Safe.Locker.entity.User.Role;
import com.aits.Safe.Locker.entity.Vehicle;
import com.aits.Safe.Locker.repo.ServiceRecordRepository;
import com.aits.Safe.Locker.repo.UserRepository;
import com.aits.Safe.Locker.repo.VehicleRepository;

@Service
public class ServiceRecordService {
	
	@Autowired
	ServiceRecordRepository serviceRecordRepository;
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	public String addServiceRecord(ServiceRecord serviceRecord) {
	    Optional<Vehicle> vehicleOpt = vehicleRepository.findById(serviceRecord.getVehicle().getId());
	    if (vehicleOpt.isEmpty()) {
	        return "Vehicle with given ID is not found";
	    }

	    Optional<User> userOpt = userRepository.findById(serviceRecord.getUser().getId());
	    if (userOpt.isEmpty() || userOpt.get().getRole() != Role.SERVICE_ADVISOR) {
	        return "Service advisor with given ID is not found or role is not SERVICE_ADVISOR";
	    }

	    serviceRecord.setVehicle(vehicleOpt.get());
	    serviceRecord.setUser(userOpt.get());
	    serviceRecord.setStatus(Status.PENDING);
	    serviceRecordRepository.save(serviceRecord);

	    return "Service Record is added";
	}


	public String updateServiceRecord(long id) {
		
		ServiceRecord serviceRecord=serviceRecordRepository.findById(id).get();
		serviceRecord.setServiceDate(LocalDateTime.now());
		serviceRecord.setStatus(Status.COMPLETED);
		serviceRecordRepository.save(serviceRecord);
		return "Service Record is updated";
	}


}
