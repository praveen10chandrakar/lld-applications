package io.praveen10.applications.payment.service;

import io.praveen10.applications.parkinglot.common.enums.TransactionStatus;
import io.praveen10.applications.parkinglot.common.exceptions.PaymentException;
import io.praveen10.applications.payment.model.Transaction;
import io.praveen10.applications.payment.policy.RetryPolicy;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {

    private Map<String, Transaction> transactions;
    private RetryPolicy retryPolicy;

    public PaymentService() {
        transactions = new HashMap<>();
        retryPolicy = new RetryPolicy();
    }

    public void processPayment(String idempotencyKey, Payment paymentMethod, String uniqueId, double charges) throws PaymentException {
        if (transactions.containsKey(idempotencyKey)) {
            throw new PaymentException("Transaction already processed");
        }

        Transaction transaction = new Transaction(idempotencyKey, uniqueId, charges);
        transactions.put(idempotencyKey, transaction);

        if (retryPolicy.retryPayment(paymentMethod, charges)) {
            transaction.setStatus(TransactionStatus.SUCCESS);
            System.out.println("Transaction success for transaction Id: " + idempotencyKey);
        } else {
            transaction.setStatus(TransactionStatus.FAILED);
            System.out.println("Transaction failed for transaction Id: " + idempotencyKey);
            paymentMethod.rollback();
        }
    }

}
