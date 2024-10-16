package io.praveen10.applications.parkinglot.parking.model.parking;

import io.praveen10.applications.parkinglot.common.enums.ParkingSpotType;

public class BikeParkingSpot extends ParkingSpot {
    public BikeParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.BIKE);
    }
}
