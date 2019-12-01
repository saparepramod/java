package com.ps.park;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.ps.park.entities.ParkingSpot;
import com.ps.park.repo.ParkingSpotRepo;

public class ParkingReservationServiceImpl implements ParkingReservationService {

	@Autowired
	ParkingSpotRepo repository;
	
	@Override
	public List<ParkingSpot> getAvailableParkingSpots() {
		List<ParkingSpot> availableSpots = repository.findByAvailable(1);
		return availableSpots;
	}

	@Override
	public List<ParkingSpot> getNearestParkingSpots(double lat, double lon, double radius) {
		List<ParkingSpot> nearestSpots = new ArrayList<ParkingSpot>();
		
		List<ParkingSpot> availableSpots = getAvailableParkingSpots();
		
		for (ParkingSpot parkingSpot : availableSpots) {
			double distance = distance(lat, lon,
					parkingSpot.getLatitude(), parkingSpot.getLongitude());
			if (distance <= radius) {
				nearestSpots.add(parkingSpot);
			}
		}
		return nearestSpots;
	}
	
	@Override
	public ParkingSpot reserveParkingSpot(ParkingSpot parkingSpot) {
		parkingSpot.setAvailable(0);
		parkingSpot.setStarttime(System.currentTimeMillis());
		repository.save(parkingSpot);
		return parkingSpot;
	}

	@Override
	public List<ParkingSpot> getReservedParkingSpots() {
		List<ParkingSpot> reservedSpots = repository.findByAvailable(0);
		return reservedSpots;
	}

	@Override
	public ParkingSpot getReservedParkingSpot(String vehicle) {
		ParkingSpot reservedSpot = repository.findByVehicle(vehicle);
		return reservedSpot;
	}

	@Override
	public ParkingSpot cancelReservedParkingSpot(ParkingSpot parkingSpot) {

		parkingSpot.setAvailable(1);
		parkingSpot.setEndtime(System.currentTimeMillis());
		
		repository.save(parkingSpot);
		return parkingSpot;
	}

	@Override
	public double getReservationCost(String vehicle) {

		ParkingSpot parkingSpot = repository.findByVehicle(vehicle);
		
		double totalReservedMillis = parkingSpot.getEndtime() - parkingSpot.getStarttime();
		double totalReservedHours = Math.ceil(totalReservedMillis/3600000);
		double cost = totalReservedHours * parkingSpot.getParkingrate();
		
		return cost;
	}
	
	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		} else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			// converting distance to meters
			dist = dist * 60 * 1.1515 * 1.609344 * 1000;
			return (dist);
		}
	}
	
	@Override
	public ParkingSpot createParkingSpot(ParkingSpot parkingSpot) {
		repository.save(parkingSpot);
		return parkingSpot;
	}

	// Additional test services
	@Override
	public Response getStatus() {
		return Response.ok().build();
	}
	
	@Override
	public List<ParkingSpot> getAllParkingSpots() {
		return repository.findAll();
	}

	List<ParkingSpot> spots = new ArrayList<>();
	@Override
	public ParkingSpot createInMemoryParkingSpot(ParkingSpot parkingSpot) {
		spots.add(parkingSpot);
		return parkingSpot;
	}

}
