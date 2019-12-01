package com.ps.park.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.park.entities.ParkingSpot;

public interface ParkingSpotRepo extends JpaRepository<ParkingSpot, Integer> {

	List<ParkingSpot> findByAvailable(int available);
	ParkingSpot findByVehicle(String vehicle);
}
