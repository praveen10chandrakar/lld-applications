package io.praveen10.applications.parkinglot.parking.model.vehicle;

import io.praveen10.applications.parkinglot.common.enums.VehicleType;

public class ElectricCar extends Vehicle {
    public ElectricCar(String registrationNo) {
        super(registrationNo, VehicleType.ELECTRIC_CAR);
    }
}
