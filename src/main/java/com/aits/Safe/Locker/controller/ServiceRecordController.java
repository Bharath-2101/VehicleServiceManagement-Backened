package com.aits.Safe.Locker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aits.Safe.Locker.entity.ServiceRecord;
import com.aits.Safe.Locker.entity.ServiceRecord.Status;
import com.aits.Safe.Locker.exception.NullValueException;
import com.aits.Safe.Locker.service.ServiceRecordService;

@RestController
public class ServiceRecordController {
    
    @Autowired
    ServiceRecordService serviceRecordService;

    @PostMapping("/admin/addservicerecord")
    public String addServiceRecord(@RequestBody ServiceRecord serviceRecord) {
        return serviceRecordService.addServiceRecord(serviceRecord);
    }

    @GetMapping("/advisor/updateservicerecord")
    public String updateStatusAndServiceDate(@RequestParam long id) {
        return serviceRecordService.updateServiceRecord(id);
    }

    @PutMapping("/admin/updateservicerecord")
    public String updateServiceRecord(@RequestBody ServiceRecord serviceRecord) {
        if (serviceRecord.getId() == null) {
            throw new NullValueException("Service record ID cannot be null.");
        }
        return serviceRecordService.updateServiceRecord(serviceRecord);
    }

    @GetMapping("/admin/getservicerecord")
    public ServiceRecord getServiceRecordById(@RequestParam long id) {
        return serviceRecordService.getServiceRecordById(id);
    }

    @GetMapping("/admin/allservicerecords")
    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordService.getAllServiceRecords();
    }


    @GetMapping("/admin/servicerecords")
    public List<ServiceRecord> getServiceRecordsByStatus(@RequestParam Status status) {
        return serviceRecordService.getServiceRecordsByStatus(status);
    }


    @GetMapping("/servicerecords")
    public List<ServiceRecord> getServiceRecordsOfUser(@RequestParam long id) {
        return serviceRecordService.getServiceRecordsOfUser(id);
    }


    @DeleteMapping("/admin/deleteservicerecord")
    public String deleteServiceRecord(@RequestParam long id) {
        return serviceRecordService.deleteServiceRecord(id);
    }
}
