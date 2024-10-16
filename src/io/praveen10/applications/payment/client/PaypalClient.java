package io.praveen10.applications.payment.client;

public class PaypalClient {

    public boolean initiatePayment(double amount) {
        System.out.println("Initiating paypal payment from client");
        // Invoke paypal API to initiate payment
        return true;
    }

    public boolean verifyPayment() {
        // Invoke paypal API to verify payment
        return true;
    }

    public boolean rollbackTransaction() {
        // Invoke paypal API to verify payment
        return true;
    }

}
