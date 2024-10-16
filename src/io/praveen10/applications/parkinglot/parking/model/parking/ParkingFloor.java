package io.praveen10.applications.parkinglot.parking.model.parking;

import io.praveen10.applications.parkinglot.common.enums.ParkingSpotType;
import io.praveen10.applications.parkinglot.common.enums.VehicleType;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ParkingFloor {
    private String floorId;
    private Map<ParkingSpotType, Deque<ParkingSpot>> parkingSpots;
    private Map<String, ParkingSpot> usedParkingSpots;

    public ParkingFloor(String floorId) {
        this.floorId = floorId;
        usedParkingSpots = new HashMap<>();
        for(ParkingSpotType parkingSpotType: ParkingSpotType.values()) {
            parkingSpots.put(parkingSpotType, new ConcurrentLinkedDeque<>());
        }
    }

    public boolean isParkingAvailable(VehicleType vehicleType) {
        return isParkingAvailable(ParkingSpotType.getParkingSpotType(vehicleType));
    }

    public boolean isParkingAvailable(ParkingSpotType parkingSpotType) {
        return parkingSpots.get(parkingSpotType).size() > 0;
    }

    public synchronized ParkingSpot bookParkingSpot(VehicleType vehicleType) {
        if(!isParkingAvailable(vehicleType))
            return null;

        ParkingSpotType parkingSpotType = ParkingSpotType.getParkingSpotType(vehicleType);
        ParkingSpot parkingSpot = parkingSpots.get(parkingSpotType).poll();
        if(Objects.nonNull(parkingSpot)) {
            usedParkingSpots.put(parkingSpot.getParkingSpotId(), parkingSpot);
            parkingSpot.setOccupied();
        }
        return parkingSpot;
    }

    public ParkingSpot releaseParkingSpot(String parkingSpotId) {
        ParkingSpot parkingSpot = usedParkingSpots.remove(parkingSpotId);
        if (Objects.nonNull(parkingSpot)) {
            parkingSpot.freeSpot();
            parkingSpots.get(parkingSpot.getParkingSpotType()).addFirst(parkingSpot);
        }
        return parkingSpot;
    }

    public String getFloorId() {
        return floorId;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        parkingSpots.get(parkingSpot.getParkingSpotType()).addFirst(parkingSpot);
    }
}
