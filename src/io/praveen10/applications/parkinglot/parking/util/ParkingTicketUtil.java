package io.praveen10.applications.parkinglot.parking.util;

import io.praveen10.applications.parkinglot.common.enums.TicketStatus;
import io.praveen10.applications.parkinglot.parking.model.parking.ParkingTicket;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingTicketUtil {

    public static ParkingTicket buildNewParkingTicket(String vehicleNumber, String parkingSpotId) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setTicketNumber(UUID.randomUUID().toString());
        parkingTicket.setIssuedAt(LocalDateTime.now());
        parkingTicket.setVehicleRegistrationNo(vehicleNumber);
        parkingTicket.setAllocatedSpotId(parkingSpotId);
        parkingTicket.setTicketStatus(TicketStatus.ACTIVE);
        return parkingTicket;
    }

}
