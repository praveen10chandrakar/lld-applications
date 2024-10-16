package io.praveen10.applications.parkinglot.payment.service.impl;

import io.praveen10.applications.parkinglot.common.exceptions.PaymentException;
import io.praveen10.applications.parkinglot.payment.service.Payment;

public class CreditCardPayment implements Payment {
    @Override
    public boolean initiatePayment(double amount) throws PaymentException {
        System.out.println("Processing credit cart payment of " + amount);
        if (amount > 10000) {
            throw new PaymentException("Credit card limit exceeded.");
        }
        return true;
    }

    @Override
    public boolean verifyPayment() {
        return true;
    }

    @Override
    public void rollback() {
        System.out.println("Rolling back credit card payment");
    }
}
