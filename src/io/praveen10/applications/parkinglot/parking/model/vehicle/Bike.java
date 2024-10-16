package io.praveen10.applications.parkinglot.parking.model.vehicle;

import io.praveen10.applications.parkinglot.common.enums.VehicleType;

public class Bike extends Vehicle {
    public Bike(String registrationNo) {
        super(registrationNo, VehicleType.BIKE);
    }
}
