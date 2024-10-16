package io.praveen10.applications.payment.service;

import io.praveen10.applications.parkinglot.common.enums.TransactionStatus;
import io.praveen10.applications.parkinglot.common.exceptions.PaymentException;
import io.praveen10.applications.parkinglot.parking.model.parking.ParkingTicket;
import io.praveen10.applications.payment.model.Transaction;
import io.praveen10.applications.payment.policy.RetryPolicy;

import java.util.HashMap;
import java.util.Map;

public class PaymentProcessor {

    private Map<String, Transaction> transactions;
    private RetryPolicy retryPolicy;

    public PaymentProcessor() {
        transactions = new HashMap<>();
        retryPolicy = new RetryPolicy();
    }

    public void processPayment(String idempotencyKey, Payment paymentMethod, ParkingTicket parkingTicket) throws PaymentException {
        if (transactions.containsKey(idempotencyKey)) {
            throw new PaymentException("Transaction already processed");
        }

        Transaction transaction = new Transaction(idempotencyKey, parkingTicket.getTicketNumber(), parkingTicket.getCharges());
        transactions.put(idempotencyKey, transaction);

        if (retryPolicy.retryPayment(paymentMethod, parkingTicket.getCharges())) {
            transaction.setStatus(TransactionStatus.SUCCESS);
            System.out.println("Transaction success for transaction Id: " + idempotencyKey);
        } else {
            transaction.setStatus(TransactionStatus.FAILED);
            System.out.println("Transaction failed for transaction Id: " + idempotencyKey);
            paymentMethod.rollback();
        }
    }

}
