package com.aits.Safe.Locker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aits.Safe.Locker.entity.BillOfMaterial;
import com.aits.Safe.Locker.exception.NullValueException;
import com.aits.Safe.Locker.service.BillOfMaterialService;

@RestController
public class BillOfMaterialController {

    @Autowired
    BillOfMaterialService billOfMaterialService;

    @PostMapping("/advisor/addbillitem")
    public String addBillItem(@RequestBody BillOfMaterial billOfMaterial) {
        return billOfMaterialService.addBillItem(billOfMaterial);
    }

    @GetMapping("/admin/billitems")
    public List<BillOfMaterial> allBillItems() {
        return billOfMaterialService.getAllBillItems();
    }

    @GetMapping("/admin/billitems/{id}")
    public List<BillOfMaterial> getBillOfMaterialsByServiceRecordId(@PathVariable long id) {
        return billOfMaterialService.getBillItemsUsingId(id);
    }

    @GetMapping("/admin/getBillItem")
    public BillOfMaterial getBillItemById(@RequestParam Long id) {
        if (id == null) {
            throw new NullValueException("Bill item ID is null");
        }
        return billOfMaterialService.getBillItemById(id);
    }

    @PutMapping("/admin/updateBillItem")
    public String updateBillItem(@RequestBody BillOfMaterial billOfMaterial) {
        if (billOfMaterial.getId() == null) {
            throw new NullValueException("Bill item ID is null");
        }
        return billOfMaterialService.updateBillItem(billOfMaterial);
    }

    @DeleteMapping("/admin/deleteBillItem")
    public String deleteBillItem(@RequestParam Long id) {
        if (id == null) {
            throw new NullValueException("Bill item ID is null");
        }
        return billOfMaterialService.deleteBillItem(id);
    }
}
