package io.praveen10.applications.parkinglot.parking.model.parking;

import io.praveen10.applications.parkinglot.common.enums.ParkingSpotType;

public enum HourlyCost {

    CAR(ParkingSpotType.CAR, 20.0),
    E_CAR(ParkingSpotType.E_CAR, 25.0),
    BIKE(ParkingSpotType.BIKE, 10.0),
    E_BIKE(ParkingSpotType.E_BIKE, 15.0),
    LARGE(ParkingSpotType.LARGE, 40.0);

    private ParkingSpotType parkingSpotType;
    private Double cost;

    HourlyCost(ParkingSpotType parkingSpotType, Double cost) {
        this.parkingSpotType = parkingSpotType;
        this.cost = cost;
    }

    public static Double getCostByParkingSpotType(ParkingSpotType parkingSpotType) {
        for (HourlyCost hourlyCost: HourlyCost.values()) {
            if(hourlyCost.parkingSpotType == parkingSpotType) {
                return hourlyCost.cost;
            }
        }
        return 0.0;
    }

}
