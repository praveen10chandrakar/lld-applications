package io.praveen10.applications.payment.service.impl;

import io.praveen10.applications.parkinglot.common.exceptions.PaymentException;
import io.praveen10.applications.payment.client.PaypalClient;
import io.praveen10.applications.payment.service.Payment;

public class PaypalPayment implements Payment {

    private PaypalClient paypalClient;

    public PaypalPayment(PaypalClient paypalClient) {
        this.paypalClient = paypalClient;
    }

    @Override
    public boolean initiatePayment(double amount) throws PaymentException {
        System.out.println("Processing paypal payment of " + amount);
        paypalClient.initiatePayment(amount);
        return true;
    }

    @Override
    public boolean verifyPayment() {
        return paypalClient.verifyPayment();
    }

    @Override
    public void rollback() {
        paypalClient.rollbackTransaction();
        System.out.println("Rolling back paypal payment");
    }
}
