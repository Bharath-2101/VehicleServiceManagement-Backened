package com.aits.Safe.Locker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aits.Safe.Locker.DTO.VehicleDTO;
import com.aits.Safe.Locker.entity.Vehicle;
import com.aits.Safe.Locker.service.VehicleService;

@RestController
public class VehicleController {
	
	@Autowired
	VehicleService vehicleservice;

	@PostMapping("/admin/addvehicle")
	public String addVehicle(@RequestBody Vehicle vehicle)
	{
		return vehicleservice.addVehicle(vehicle);
	}
	
	@GetMapping("/advisor/servicevehicle")
	public String updateServiceDates(@RequestParam long id)
	{
		return vehicleservice.updateVehicle(id);
	}
	
	@GetMapping("/admin/due")
    public List<VehicleDTO> getDueForService() {
        return vehicleservice.getVehiclesDueThisWeek();
    }
}
