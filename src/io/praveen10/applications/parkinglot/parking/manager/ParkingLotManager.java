package io.praveen10.applications.parkinglot.parking.manager;

import io.praveen10.applications.parkinglot.common.exceptions.InvalidParkingFloorException;
import io.praveen10.applications.parkinglot.parking.model.parking.ParkingFloor;
import io.praveen10.applications.parkinglot.parking.model.parking.ParkingSpot;
import io.praveen10.applications.parkinglot.parking.model.parking.ParkingTicket;
import io.praveen10.applications.parkinglot.parking.model.vehicle.Vehicle;

public interface ParkingLotManager {

    void addParkingFloor(ParkingFloor parkingFloor);

    void addParkingSpot(String floorId, ParkingSpot parkingSpot) throws InvalidParkingFloorException;

    ParkingTicket bookParkingSlot(Vehicle vehicle);

    ParkingTicket releaseParkingSlot(ParkingTicket parkingTicket);
}
