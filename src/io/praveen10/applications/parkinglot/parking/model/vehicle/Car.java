package io.praveen10.applications.parkinglot.parking.model.vehicle;

import io.praveen10.applications.parkinglot.common.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String registrationNo) {
        super(registrationNo, VehicleType.CAR);
    }
}
