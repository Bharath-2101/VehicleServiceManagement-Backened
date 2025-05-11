package com.aits.Safe.Locker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aits.Safe.Locker.entity.ServiceRecord;
import com.aits.Safe.Locker.service.ServiceRecordService;

@RestController
public class ServiceRecordController {
	
	@Autowired
	ServiceRecordService serviceRecordService;
	
	
	@PostMapping("/admin/addservicerecord")
	public String addServiceRecord(@RequestBody ServiceRecord serviceRecord)
	{
		return serviceRecordService.addServiceRecord(serviceRecord);
	}
	
	@GetMapping("/advisor/updateservicerecord")
	public String updateStatusandServiceDate(@RequestParam long id)
	{
		return serviceRecordService.updateServiceRecord(id);
	}

}
