package io.praveen10.applications.parkinglot.parking.model.vehicle;

import io.praveen10.applications.parkinglot.common.enums.VehicleType;

public class ElectricBike extends Vehicle {

    public ElectricBike(String registrationNo) {
        super(registrationNo, VehicleType.ELECTRIC_BIKE);
    }
}
