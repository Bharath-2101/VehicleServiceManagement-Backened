package com.aits.Safe.Locker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aits.Safe.Locker.entity.BillOfMaterial;
import com.aits.Safe.Locker.entity.ServiceRecord;
import com.aits.Safe.Locker.entity.WorkItem;
import com.aits.Safe.Locker.exception.BillOfMaterialNotFoundException;
import com.aits.Safe.Locker.exception.ServiceRecordNotFoundException;
import com.aits.Safe.Locker.exception.WorkItemNotFoundException;
import com.aits.Safe.Locker.repo.BillOfMaterialRepository;
import com.aits.Safe.Locker.repo.ServiceRecordRepository;
import com.aits.Safe.Locker.repo.WorkItemRepository;

@Service
public class BillOfMaterialService {

    @Autowired
    private BillOfMaterialRepository billOfMaterialRepository;

    @Autowired
    private WorkItemRepository workItemRepository;

    @Autowired
    private ServiceRecordRepository serviceRecordRepository;

    public String addBillItem(BillOfMaterial billOfMaterial) {
        WorkItem workItem = workItemRepository.findById(billOfMaterial.getWorkItem().getId())
                .orElseThrow(() -> new WorkItemNotFoundException("Work item not found with ID: " + billOfMaterial.getWorkItem().getId()));

        ServiceRecord serviceRecord = serviceRecordRepository.findById(billOfMaterial.getServiceRecord().getId())
                .orElseThrow(() -> new ServiceRecordNotFoundException("Service record not found with ID: " + billOfMaterial.getServiceRecord().getId()));

        billOfMaterial.setServiceRecord(serviceRecord);
        billOfMaterial.setWorkItem(workItem);
        billOfMaterialRepository.save(billOfMaterial);
        return "Bill of Material is added successfully";
    }

    public List<BillOfMaterial> getAllBillItems() {
        return billOfMaterialRepository.findAll();
    }

    public List<BillOfMaterial> getBillItemsUsingId(long serviceRecordId) {
        return billOfMaterialRepository.findByServiceRecordId(serviceRecordId);
    }

    public BillOfMaterial getBillItemById(long id) {
        return billOfMaterialRepository.findById(id)
                .orElseThrow(() -> new BillOfMaterialNotFoundException("Bill of Material with ID " + id + " not found."));
    }

    public String updateBillItem(BillOfMaterial updatedBill) {
        BillOfMaterial existingBill = billOfMaterialRepository.findById(updatedBill.getId())
                .orElseThrow(() -> new BillOfMaterialNotFoundException("Bill of Material with ID " + updatedBill.getId() + " not found."));

        WorkItem workItem = workItemRepository.findById(updatedBill.getWorkItem().getId())
                .orElseThrow(() -> new WorkItemNotFoundException("Work item not found with ID: " + updatedBill.getWorkItem().getId()));

        ServiceRecord serviceRecord = serviceRecordRepository.findById(updatedBill.getServiceRecord().getId())
                .orElseThrow(() -> new ServiceRecordNotFoundException("Service record not found with ID: " + updatedBill.getServiceRecord().getId()));

        existingBill.setQuantity(updatedBill.getQuantity());
        existingBill.setWorkItem(workItem);
        existingBill.setServiceRecord(serviceRecord);

        billOfMaterialRepository.save(existingBill);
        return "Bill of Material updated successfully.";
    }

    public String deleteBillItem(long id) {
        if (!billOfMaterialRepository.existsById(id)) {
            throw new BillOfMaterialNotFoundException("Bill of Material with ID " + id + " does not exist.");
        }
        billOfMaterialRepository.deleteById(id);
        return "Bill of Material deleted successfully.";
    }
}
