package com.aits.Safe.Locker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.entity.BillOfMaterial;
import com.aits.Safe.Locker.entity.ServiceRecord;
import com.aits.Safe.Locker.entity.WorkItem;
import com.aits.Safe.Locker.repo.BillOfMaterialRepository;
import com.aits.Safe.Locker.repo.ServiceRecordRepository;
import com.aits.Safe.Locker.repo.WorkItemRepository;

@Service
public class BillOfMaterialService {

	@Autowired
	BillOfMaterialRepository billOfMaterialRepository;

	@Autowired
	WorkItemRepository workItemRepository;
	
	@Autowired
	ServiceRecordRepository serviceRecordRepository;
	
	public String addBillItem(BillOfMaterial billOfMaterial) {
		WorkItem workItem=workItemRepository.findById(billOfMaterial.getWorkItem().getId()).get();
		Optional<ServiceRecord> serviceRecord=serviceRecordRepository.findById(billOfMaterial.getServiceRecord().getId());
		if(serviceRecord.isEmpty())
		{
			return"No Service Record is found ";
		}
		if(workItem==null)
		{
			return "No work item is found";
		}
		billOfMaterial.setServiceRecord(serviceRecord.get());
		billOfMaterial.setWorkItem(workItem);
		billOfMaterialRepository.save(billOfMaterial);
		return "Bill Of Material is added successfully";
	}

	public List<BillOfMaterial> getAllBillItems() {
		return billOfMaterialRepository.findAll();
	}
	
}
