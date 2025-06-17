package com.aits.Safe.Locker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.entity.WorkItem;
import com.aits.Safe.Locker.entity.WorkItem.Type;
import com.aits.Safe.Locker.exception.WorkItemNotFoundException;
import com.aits.Safe.Locker.repo.WorkItemRepository;

@Service
public class WorkItemService {

    @Autowired
    private WorkItemRepository workItemRepository;

    public String addWorkItem(WorkItem workItem) {
        Optional<WorkItem> existingItem = workItemRepository.findByNameAndType(workItem.getName(), workItem.getType());

        if (existingItem.isPresent()) {
            WorkItem item = existingItem.get();
            item.setCost(workItem.getCost());
            item.setType(workItem.getType());
            workItemRepository.save(item);
            return "Work item updated successfully.";
        }

        workItemRepository.save(workItem);
        return "Work item added successfully.";
    }

    public List<WorkItem> getAllWorkItems() {
        return workItemRepository.findAll();
    }

    public WorkItem getWorkItemById(long id) {
        return workItemRepository.findById(id)
                .orElseThrow(() -> new WorkItemNotFoundException("Work item with ID " + id + " not found."));
    }

    public WorkItem getWorkItemByNameAndType(String name, String type) {
        return workItemRepository.findByNameAndType(name, Type.valueOf(type))
                .orElseThrow(() -> new WorkItemNotFoundException("Work item with name '" + name + "' and type '" + type + "' does not exist."));
    }

    public List<WorkItem> getAllWorkItemsByType(String type) {
        return workItemRepository.findByType(Type.valueOf(type));
    }

    public String updateWorkItem(WorkItem updatedItem) {
        WorkItem existingItem = workItemRepository.findById(updatedItem.getId())
                .orElseThrow(() -> new WorkItemNotFoundException("Work item with ID " + updatedItem.getId() + " not found."));

        existingItem.setName(updatedItem.getName());
        existingItem.setType(updatedItem.getType());
        existingItem.setCost(updatedItem.getCost());

        workItemRepository.save(existingItem);
        return "Work item updated successfully.";
    }

    public String deleteWorkItem(long id) {
        if (!workItemRepository.existsById(id)) {
            throw new WorkItemNotFoundException("Work item with ID " + id + " does not exist.");
        }

        workItemRepository.deleteById(id);
        return "Work item deleted successfully.";
    }
}
