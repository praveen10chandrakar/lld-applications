package io.praveen10.applications.parkinglot.common.enums;

public enum ParkingSpotType {
    BIKE,
    CAR,
    LARGE,
    E_CAR,
    E_BIKE;

    public static ParkingSpotType getParkingSpotType(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return CAR;
            case ELECTRIC_CAR:
                return E_CAR;
            case BIKE:
                return BIKE;
            case ELECTRIC_BIKE:
                return E_BIKE;
            default:
                return LARGE;
        }
    }
}
