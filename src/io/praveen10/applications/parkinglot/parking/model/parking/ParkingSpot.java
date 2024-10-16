package io.praveen10.applications.parkinglot.parking.model.parking;

import io.praveen10.applications.parkinglot.common.enums.ParkingSpotType;

public abstract class ParkingSpot {
    private String parkingSpotId;
    private boolean available;
    private ParkingSpotType parkingSpotType;
    private String assignedVehicleId;

    public ParkingSpot(String parkingSpotId, ParkingSpotType parkingSpotType) {
        this.parkingSpotId = parkingSpotId;
        this.parkingSpotType = parkingSpotType;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void assignVehicleToSpot(String vehicleId) {
        available = false;
        assignedVehicleId = vehicleId;
    }

    public void freeSpot() {
        available = true;
        assignedVehicleId = null;
    }

    public void setOccupied() {
        this.available = false;
    }
}
