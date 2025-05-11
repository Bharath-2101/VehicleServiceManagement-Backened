package com.aits.Safe.Locker.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime serviceDate;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "serviceRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillOfMaterial> billOfMaterials;

    public ServiceRecord() {}

    

    public ServiceRecord(Long id, String name, LocalDateTime serviceDate, Status status, Vehicle vehicle, User user,
			List<BillOfMaterial> billOfMaterials) {
		super();
		this.id = id;
		this.name = name;
		this.serviceDate = serviceDate;
		this.status = status;
		this.vehicle = vehicle;
		this.user = user;
		this.billOfMaterials = billOfMaterials;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDateTime serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BillOfMaterial> getBillOfMaterials() {
        return billOfMaterials;
    }

    public void setBillOfMaterials(List<BillOfMaterial> billOfMaterials) {
        this.billOfMaterials = billOfMaterials;
    }
    
    public enum Status{
    	PENDING,COMPLETED
    }
}
