package com.aits.Safe.Locker.DTO;

import java.time.LocalDate;

import com.aits.Safe.Locker.entity.Vehicle.Fuel;
import com.aits.Safe.Locker.entity.Vehicle.Type;

public class VehicleDTO {
	
	 private String model;
	    private String registrationNumber;
	    private int year;
	    private LocalDate lastServiceDate;
	    private LocalDate nextServiceDate;
	    private Type type;
	    private Fuel fuel;
	    private CustomerDTO customer;
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
	    
	    

}
