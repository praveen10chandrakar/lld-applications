package io.praveen10.applications.parkinglot.parking.model.parking;

import io.praveen10.applications.parkinglot.common.enums.TicketStatus;

import java.time.LocalDateTime;

public class ParkingTicket {
    private String ticketNumber;
    private String vehicleRegistrationNo;
    private String allocatedSpotId;
    private LocalDateTime issuedAt;
    private LocalDateTime vacatedAt;
    private double charges;
    private TicketStatus ticketStatus;

    public String getTicketNumber() {
        return ticketNumber;
    }

    public double getCharges() {
        return charges;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public LocalDateTime getVacatedAt() {
        return vacatedAt;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setVehicleRegistrationNo(String vehicleRegistrationNo) {
        this.vehicleRegistrationNo = vehicleRegistrationNo;
    }

    public String getAllocatedSpotId() {
        return allocatedSpotId;
    }

    public void setAllocatedSpotId(String allocatedSpotId) {
        this.allocatedSpotId = allocatedSpotId;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public void setVacatedAt(LocalDateTime vacatedAt) {
        this.vacatedAt = vacatedAt;
    }
}
