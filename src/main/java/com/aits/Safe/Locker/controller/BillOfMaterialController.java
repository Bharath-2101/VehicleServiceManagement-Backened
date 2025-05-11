package com.aits.Safe.Locker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aits.Safe.Locker.entity.BillOfMaterial;
import com.aits.Safe.Locker.service.BillOfMaterialService;

@RestController
public class BillOfMaterialController {

	@Autowired
	BillOfMaterialService billOfMaterialService;
	
	@PostMapping("/advisor/addbillitem")
	public String addBillItem(@RequestBody BillOfMaterial billOfMaterial)
	{
		return billOfMaterialService.addBillItem(billOfMaterial);
	}
	
	@GetMapping("/admin/billitems")
	public List<BillOfMaterial> allBillItems()
	{
		return billOfMaterialService.getAllBillItems();
	}
}

