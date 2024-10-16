package io.praveen10.applications.parkinglot.services.impl;

import io.praveen10.applications.parkinglot.account.model.Account;
import io.praveen10.applications.parkinglot.common.enums.VehicleType;
import io.praveen10.applications.parkinglot.common.exceptions.AccountException;
import io.praveen10.applications.parkinglot.common.exceptions.PaymentException;
import io.praveen10.applications.parkinglot.parking.manager.impl.ParkingLotManagerImpl;
import io.praveen10.applications.parkinglot.parking.model.parking.ParkingTicket;
import io.praveen10.applications.parkinglot.parking.model.vehicle.Vehicle;
import io.praveen10.applications.parkinglot.parking.model.vehicle.VehicleFactory;
import io.praveen10.applications.payment.service.Payment;
import io.praveen10.applications.payment.service.PaymentService;
import io.praveen10.applications.parkinglot.services.ParkingLotService;

import java.util.UUID;

public class ParkingLotServiceImpl implements ParkingLotService {

    private ParkingLotManagerImpl parkingLotManager;
    private PaymentService paymentService;

    public ParkingLotServiceImpl(ParkingLotManagerImpl parkingLotManager, PaymentService paymentService) {
        this.parkingLotManager = parkingLotManager;
        this.paymentService = paymentService;
    }

    @Override
    public ParkingTicket bookParking(Account account, String regNo, VehicleType vehicleType) throws AccountException {
        validateAdmin(account);
        Vehicle vehicle = VehicleFactory.createVehicle(vehicleType, regNo);
        ParkingTicket parkingTicket = parkingLotManager.bookParkingSlot(vehicle);
        return parkingTicket;
    }

    @Override
    public boolean releaseParking(Account account, ParkingTicket parkingTicket, Payment paymentMode) throws AccountException {
        validateAdmin(account);
        String transactionId = UUID.randomUUID().toString();
        try {
            paymentService.processPayment(transactionId, paymentMode, parkingTicket.getTicketNumber(), parkingTicket.getCharges());
        } catch (PaymentException paymentException) {
            System.out.println("Error occurred while processing payment..");
            return false;
        }
        parkingLotManager.releaseParkingSlot(parkingTicket);
        return true;
    }

    private void validateAdmin(Account account) throws AccountException{
        if (!account.isAdmin()) {
            throw new AccountException("Invalid admin.");
        }
    }
}
