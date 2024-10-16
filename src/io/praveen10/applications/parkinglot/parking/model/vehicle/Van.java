package io.praveen10.applications.parkinglot.parking.model.vehicle;

import io.praveen10.applications.parkinglot.common.enums.VehicleType;

public class Van extends Vehicle {
    public Van(String registrationNo) {
        super(registrationNo, VehicleType.VAN);
    }
}
