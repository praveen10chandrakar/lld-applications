package io.praveen10.applications.parkinglot.parking.model.parking;

import io.praveen10.applications.parkinglot.common.enums.ParkingSpotType;

public class ElectricBikeParkingSpot extends ParkingSpot {
    public ElectricBikeParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.E_BIKE);
    }
}
