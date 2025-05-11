package com.aits.Safe.Locker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.entity.WorkItem;
import com.aits.Safe.Locker.repo.WorkItemRepository;

@Service
public class WorkItemService {
	
	@Autowired
	WorkItemRepository workItemRepository;
	
	public String addWorkItem(WorkItem workItem)
	{
		Optional<WorkItem> existingItem=workItemRepository.findByNameAndType(workItem.getName(),workItem.getType());
		
		if(!existingItem.isEmpty())
		{
			WorkItem item=existingItem.get();
			item.setCost(workItem.getCost());
			item.setType(workItem.getType());
			
			workItemRepository.save(item);
			
			return "Work Item Updated Successfully";
		}
		
		workItemRepository.save(workItem);
		return "Work Item is added Successfully";
		
	}
	
	public List<WorkItem> getAllWorkItems()
	{
		return workItemRepository.findAll();
	}
}
