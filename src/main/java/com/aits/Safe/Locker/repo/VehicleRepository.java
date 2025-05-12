package com.aits.Safe.Locker.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aits.Safe.Locker.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long>{
	
	Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
	List<Vehicle> findByNextServiceDateBetween(LocalDate start, LocalDate end);

}
