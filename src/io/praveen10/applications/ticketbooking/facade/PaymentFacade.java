package io.praveen10.applications.ticketbooking.facade;

import io.praveen10.applications.parkinglot.common.exceptions.PaymentException;
import io.praveen10.applications.payment.service.Payment;
import io.praveen10.applications.ticketbooking.model.Booking;
import io.praveen10.applications.ticketbooking.services.BookingPaymentService;
import io.praveen10.applications.ticketbooking.services.BookingService;

public class PaymentFacade {

    private final BookingPaymentService paymentService;
    private final BookingService bookingService;

    public PaymentFacade(BookingPaymentService paymentService, BookingService bookingService) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;
    }

    public void processPayment(final String bookingId, final String user,
                               final Payment paymentMethod, final double amount) throws PaymentException {
        Booking booking = bookingService.getBooking(bookingId);
        paymentService.processPayment(paymentMethod, booking, amount, user);
    }
}
