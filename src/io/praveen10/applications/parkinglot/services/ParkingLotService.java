package io.praveen10.applications.parkinglot.services;

import io.praveen10.applications.parkinglot.account.model.Account;
import io.praveen10.applications.parkinglot.common.enums.VehicleType;
import io.praveen10.applications.parkinglot.common.exceptions.AccountException;
import io.praveen10.applications.parkinglot.parking.model.parking.ParkingTicket;
import io.praveen10.applications.parkinglot.payment.service.Payment;

public interface ParkingLotService {

    ParkingTicket bookParking(Account account, String regNo, VehicleType vehicleType) throws AccountException;

    boolean releaseParking(Account account, ParkingTicket parkingTicket, Payment paymentMode) throws AccountException;

}
