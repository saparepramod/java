package com.ps.park;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ps.park.entities.ParkingSpot;

@Consumes("application/json")
@Produces("application/json")
@Path("/parkingreservationservice")
public interface ParkingReservationService {

	@Path("/availablespots")
	@GET
	List<ParkingSpot> getAvailableParkingSpots();

	@Path("/nearestspots")
	@GET
	List<ParkingSpot> getNearestParkingSpots(@HeaderParam("lat") double lat,
			@HeaderParam("lon") double lon, @HeaderParam("radius") double radius);
	
	@Path("/reserve")
	@PUT
	ParkingSpot reserveParkingSpot(ParkingSpot parkingSpot);

	@Path("/reservedspots")
	@GET
	List<ParkingSpot> getReservedParkingSpots();
	
	@Path("/myspot/{vehicle}")
	@GET
	ParkingSpot getReservedParkingSpot(@PathParam("vehicle") String vehicle);

	@Path("/cancel")
	@PUT
	ParkingSpot cancelReservedParkingSpot(ParkingSpot parkingSpot);
	
	@Path("/cost/{vehicle}")
	@GET
	double getReservationCost(@PathParam("vehicle") String vehicle);
	
	@Path("/newspot")
	@POST
	ParkingSpot createParkingSpot(ParkingSpot parkingSpot);
	
	// Additional test services
	@Path("/status")
	@GET
	Response getStatus();
	
	@Path("/allspots")
	@GET
	List<ParkingSpot> getAllParkingSpots();
	
	@Path("/createinmemoryspot")
	@POST
	ParkingSpot createInMemoryParkingSpot(ParkingSpot parkingSpot);
}
