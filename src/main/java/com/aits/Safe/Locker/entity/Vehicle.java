package com.aits.Safe.Locker.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String registrationNumber;
    private int year;
    private LocalDate lastServiceDate;
    private LocalDate nextServiceDate;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ServiceRecord> serviceRecords;

    public Vehicle() {}

    

    public LocalDate getLastServiceDate() {
		return lastServiceDate;
	}



	public void setLastServiceDate(LocalDate lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}



	public LocalDate getNextServiceDate() {
		return nextServiceDate;
	}



	public void setNextServiceDate(LocalDate nextServiceDate) {
		this.nextServiceDate = nextServiceDate;
	}



	public Vehicle(Long id, String model, String registrationNumber, int year, LocalDate lastServiceDate,
			LocalDate nextServiceDate, Type type, Fuel fuel, Customer customer, List<ServiceRecord> serviceRecords) {
		super();
		this.id = id;
		this.model = model;
		this.registrationNumber = registrationNumber;
		this.year = year;
		this.lastServiceDate = lastServiceDate;
		this.nextServiceDate = nextServiceDate;
		this.type = type;
		this.fuel = fuel;
		this.customer = customer;
		this.serviceRecords = serviceRecords;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ServiceRecord> getServiceRecords() {
        return serviceRecords;
    }

    public void setServiceRecords(List<ServiceRecord> serviceRecords) {
        this.serviceRecords = serviceRecords;
    }

    public enum Type {
        CAR, BIKE
    }

    public enum Fuel {
        PETROL, DIESEL
    }
}
