package com.aits.Safe.Locker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aits.Safe.Locker.entity.Vehicle;
import com.aits.Safe.Locker.exception.NullValueException;
import com.aits.Safe.Locker.service.VehicleService;

@RestController
public class VehicleController {
    
    @Autowired
    VehicleService vehicleservice;

    @PostMapping("/admin/addvehicle")
    public String addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleservice.addVehicle(vehicle);
    }

    @GetMapping("/advisor/servicevehicle")
    public String updateServiceDates(@RequestParam long id) {
        return vehicleservice.updateServiceDatesVehicle(id);
    }

    @GetMapping("/admin/allvehicles/due")
    public List<Vehicle> getDueForService() {
        return vehicleservice.getVehiclesDueThisWeek();
    }

    @GetMapping("admin/allvehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleservice.getAllVehicles();
    }

    @GetMapping("/admin/getvehicle")
    public Vehicle getVehicleById(@RequestParam Long id) {
        if (id == null) {
            throw new NullValueException("Vehicle ID is null");
        }
        return vehicleservice.getVehicleById(id);
    }

    @PutMapping("/admin/updatevehicle")
    public String updateVehicle(@RequestBody Vehicle vehicle) {
        if (vehicle.getId() == null) {
            throw new NullValueException("Vehicle ID is null");
        }
        return vehicleservice.updateVehicle(vehicle);
    }

    @DeleteMapping("/admin/deletevehicle")
    public String deleteVehicle(@RequestParam Long id) {
        if (id == null) {
            throw new NullValueException("Vehicle ID is null");
        }
        return vehicleservice.deleteVehicle(id);
    }
}
