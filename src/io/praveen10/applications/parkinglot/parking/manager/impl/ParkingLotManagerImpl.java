package io.praveen10.applications.parkinglot.parking.manager.impl;

import io.praveen10.applications.parkinglot.common.enums.VehicleType;
import io.praveen10.applications.parkinglot.common.exceptions.InvalidParkingFloorException;
import io.praveen10.applications.parkinglot.parking.model.parking.ParkingFloor;
import io.praveen10.applications.parkinglot.parking.model.parking.ParkingSpot;
import io.praveen10.applications.parkinglot.parking.model.parking.ParkingTicket;
import io.praveen10.applications.parkinglot.parking.model.vehicle.Vehicle;
import io.praveen10.applications.parkinglot.parking.manager.ParkingLotManager;
import io.praveen10.applications.parkinglot.parking.util.ParkingCostCalculatorUtil;
import io.praveen10.applications.parkinglot.parking.util.ParkingTicketUtil;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotManagerImpl implements ParkingLotManager {

    private Map<String, ParkingFloor> parkingFloors;

    public ParkingLotManagerImpl() {
        parkingFloors = new ConcurrentHashMap<>();
    }

    @Override
    public void addParkingFloor(ParkingFloor parkingFloor) {
        if(parkingFloors.containsKey(parkingFloor))
            return;

        parkingFloors.put(parkingFloor.getFloorId(), parkingFloor);
    }

    @Override
    public void addParkingSpot(String floorId, ParkingSpot parkingSpot) throws InvalidParkingFloorException {

        ParkingFloor floor = parkingFloors.get(floorId);
        if (Objects.isNull(floor)) {
            throw new InvalidParkingFloorException("Invalid Floor");
        }

        floor.addParkingSpot(parkingSpot);
    }

    @Override
    public ParkingTicket bookParkingSlot(Vehicle vehicle) {
        if(isParkingAvailable(vehicle.getVehicleType())) {
            return null;
        }

        ParkingSpot parkingSpot = bookParkingSpot(vehicle.getVehicleType());
        if(Objects.isNull(parkingSpot)) {
            return null;
        }
        return ParkingTicketUtil.buildNewParkingTicket(vehicle.getRegistrationNo(), parkingSpot.getParkingSpotId());
    }

    @Override
    public ParkingTicket releaseParkingSlot(ParkingTicket parkingTicket) {
        ParkingSpot parkingSpot = vacateParkingSpot(parkingTicket.getAllocatedSpotId());
        parkingTicket.setVacatedAt(LocalDateTime.now());
        parkingTicket.setCharges(ParkingCostCalculatorUtil.calculateCharges(parkingTicket, parkingSpot.getParkingSpotType()));
        return parkingTicket;
    }

    private boolean isParkingAvailable(VehicleType vehicleType) {
        return parkingFloors.values().stream().anyMatch(floor -> floor.isParkingAvailable(vehicleType));
    }

    private ParkingSpot bookParkingSpot(VehicleType vehicleType) {
        for (ParkingFloor parkingFloor: parkingFloors.values()) {
            ParkingSpot parkingSpot = parkingFloor.bookParkingSpot(vehicleType);
            if (Objects.nonNull(parkingSpot)) {
                return parkingSpot;
            }
        }
        return null;
    }

    private ParkingSpot vacateParkingSpot(String parkingSpotId) {
        for (ParkingFloor parkingFloor: parkingFloors.values()) {
            ParkingSpot parkingSpot = parkingFloor.releaseParkingSpot(parkingSpotId);
            if (Objects.nonNull(parkingSpot)) {
                return parkingSpot;
            }
        }
        return null;
    }
}
