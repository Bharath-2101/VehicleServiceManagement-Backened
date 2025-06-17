package com.aits.Safe.Locker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aits.Safe.Locker.entity.WorkItem;
import com.aits.Safe.Locker.exception.NullValueException;
import com.aits.Safe.Locker.service.WorkItemService;

@RestController
public class WorkItemController {

    @Autowired
    WorkItemService workItemService;

    @PostMapping("/admin/addWorkitem")
    public String addWorkItem(@RequestBody WorkItem workItem) {
        return workItemService.addWorkItem(workItem);
    }

    @GetMapping("/workitems")
    public List<WorkItem> getAllWorkItems() {
        return workItemService.getAllWorkItems();
    }

    @GetMapping("/workitem")
    public WorkItem getWorkItemByNameAndType(@RequestParam String name, @RequestParam String type) {
        return workItemService.getWorkItemByNameAndType(name, type);
    }

    @GetMapping("/workitems/{type}")
    public List<WorkItem> getAllWorkItemsByType(@PathVariable String type) {
        return workItemService.getAllWorkItemsByType(type);
    }

    @GetMapping("/admin/getWorkItem")
    public WorkItem getWorkItemById(@RequestParam Long id) {
        if (id == null) {
            throw new NullValueException("Work item ID is null");
        }
        return workItemService.getWorkItemById(id);
    }

    @PutMapping("/admin/updateWorkItem")
    public String updateWorkItem(@RequestBody WorkItem workItem) {
        if (workItem.getId() == null) {
            throw new NullValueException("Work item ID is null");
        }
        return workItemService.updateWorkItem(workItem);
    }

    @DeleteMapping("/admin/deleteWorkItem")
    public String deleteWorkItem(@RequestParam Long id) {
        if (id == null) {
            throw new NullValueException("Work item ID is null");
        }
        return workItemService.deleteWorkItem(id);
    }
}
