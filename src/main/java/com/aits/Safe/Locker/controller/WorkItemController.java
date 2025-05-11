package com.aits.Safe.Locker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aits.Safe.Locker.entity.WorkItem;
import com.aits.Safe.Locker.service.WorkItemService;

@RestController
public class WorkItemController {

	@Autowired
	WorkItemService workItemService;
	
	@PostMapping("/admin/addWorkitem")
	public String addWorkItem(@RequestBody WorkItem workItem)
	{
		return workItemService.addWorkItem(workItem);
	}
	
	@GetMapping("/workitems")
	public List<WorkItem> getAllWorkItems()
	{
		return workItemService.getAllWorkItems();
	}
}
