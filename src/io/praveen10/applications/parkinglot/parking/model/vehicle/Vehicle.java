package io.praveen10.applications.parkinglot.parking.model.vehicle;

import io.praveen10.applications.parkinglot.common.enums.VehicleType;

public abstract class Vehicle {
    private String registrationNo;
    private final VehicleType vehicleType;

    public Vehicle(String registrationNo, VehicleType vehicleType) {
        this.registrationNo = registrationNo;
        this.vehicleType = vehicleType;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
