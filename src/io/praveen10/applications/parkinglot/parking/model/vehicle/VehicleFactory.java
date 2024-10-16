package io.praveen10.applications.parkinglot.parking.model.vehicle;

import io.praveen10.applications.parkinglot.common.enums.VehicleType;

public class VehicleFactory {

    public static Vehicle createVehicle(VehicleType vehicleType, String regNo) {
        switch (vehicleType) {
            case ELECTRIC_CAR:
                return new ElectricCar(regNo);
            case CAR:
                return new Car(regNo);
            case BIKE:
                return new Bike(regNo);
            case VAN:
                return new Van(regNo);
            case TRUCK:
                return new Truck(regNo);
            case ELECTRIC_BIKE:
                return new ElectricBike(regNo);
            default:
                throw new IllegalArgumentException("Invalid vehicle type " + vehicleType);
        }
    }

}
