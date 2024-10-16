package io.praveen10.applications.ticketbooking.services;

import io.praveen10.applications.parkinglot.common.exceptions.PaymentException;
import io.praveen10.applications.payment.service.Payment;
import io.praveen10.applications.payment.service.PaymentService;
import io.praveen10.applications.ticketbooking.model.Booking;
import io.praveen10.applications.ticketbooking.provider.SeatLockProvider;

import java.util.UUID;

public class BookingPaymentService {

    private final PaymentService paymentService;
    private final BookingService bookingService;
    private final SeatLockProvider seatLockProvider;

    public BookingPaymentService(PaymentService paymentService, BookingService bookingService, SeatLockProvider seatLockProvider) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;
    }

    public void processPayment(Payment paymentMethod, Booking booking, double amount, String user)  {
        String transactionId = UUID.randomUUID().toString();

        seatLockProvider.lockSeats(booking.getShow(), booking.getSeats(), user);

        try {
            paymentService.processPayment(transactionId, paymentMethod, booking.getId(), amount);
            bookingService.confirmBooking(booking, user);
        } catch (PaymentException paymentException) {
            System.out.println("Failure occurred while processing payment.");
            seatLockProvider.unlockSeats(booking.getShow(), booking.getSeats(), user);
        }
    }
}
