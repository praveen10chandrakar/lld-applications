package io.praveen10.applications.parkinglot.parking.util;

import io.praveen10.applications.parkinglot.common.enums.ParkingSpotType;
import io.praveen10.applications.parkinglot.parking.model.parking.HourlyCost;
import io.praveen10.applications.parkinglot.parking.model.parking.ParkingTicket;

import java.time.Duration;

public class ParkingCostCalculatorUtil {

    public static double calculateCharges(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
        Duration duration = Duration.between(parkingTicket.getIssuedAt(), parkingTicket.getVacatedAt());
        Long hours = duration.toHours();
        hours = Math.min(hours, 1);
        double amount = hours * HourlyCost.getCostByParkingSpotType(parkingSpotType);
        return amount;
    }

}
