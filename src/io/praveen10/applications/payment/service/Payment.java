package io.praveen10.applications.payment.service;

import io.praveen10.applications.parkinglot.common.exceptions.PaymentException;

public interface Payment {
    boolean initiatePayment(double amount) throws PaymentException;
    boolean verifyPayment();

    void rollback();
}
