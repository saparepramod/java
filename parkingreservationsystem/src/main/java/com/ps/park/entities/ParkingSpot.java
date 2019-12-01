package com.ps.park.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PARKING_SPOT")
public class ParkingSpot {

	@Id
	private int id;

	private double latitude;
	private double longitude;
	/**
	 * true if parking spot is free, else false
	 */
	private int available;
	/**
	 * vehicle registration number
	 */
	private String vehicle;
	/**
	 * parking start time in milliseconds
	 */
	private long starttime;
	/**
	 * parking end time in milliseconds
	 */
	private long endtime;
	/**
	 * hourly rate for parking
	 */
	private double parkingrate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public long getStarttime() {
		return starttime;
	}

	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}

	public long getEndtime() {
		return endtime;
	}

	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}

	public double getParkingrate() {
		return parkingrate;
	}

	public void setParkingrate(double parkingrate) {
		this.parkingrate = parkingrate;
	}

}
