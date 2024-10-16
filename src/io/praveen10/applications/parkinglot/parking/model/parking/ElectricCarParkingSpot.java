package io.praveen10.applications.parkinglot.parking.model.parking;

import io.praveen10.applications.parkinglot.common.enums.ParkingSpotType;

public class ElectricCarParkingSpot extends ParkingSpot {
    public ElectricCarParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.E_CAR);
    }
}
