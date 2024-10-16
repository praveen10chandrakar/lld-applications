package io.praveen10.applications.parkinglot.payment.policy;

import io.praveen10.applications.parkinglot.common.exceptions.PaymentException;
import io.praveen10.applications.parkinglot.payment.service.Payment;

public class RetryPolicy {
    private static final int MAX_RETRIES = 3;

    public boolean retryPayment(Payment payment, double amount) {
        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            try {
                if(payment.initiatePayment(amount)) {
                    return true;
                }
            } catch (PaymentException paymentException) {
                System.out.println("Payment failed. Retrying...");
                waitForNextAttempt(attempts);
                attempts++;
            }
        }
        return false;
    }

    private void waitForNextAttempt(int attempt) {
        try {
            Thread.sleep((long) Math.pow(2, attempt) * 1000);
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }


}
