package io.praveen10.applications.parkinglot.parking.model.vehicle;

import io.praveen10.applications.parkinglot.common.enums.VehicleType;

public class Truck extends Vehicle {
    public Truck(String registrationNo) {
        super(registrationNo, VehicleType.TRUCK);
    }
}
