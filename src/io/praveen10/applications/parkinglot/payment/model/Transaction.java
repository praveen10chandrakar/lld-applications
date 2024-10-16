package io.praveen10.applications.parkinglot.payment.model;

import io.praveen10.applications.parkinglot.common.enums.TransactionStatus;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String ticketId;
    private double amount;

    private LocalDateTime initiatedDate;
    private LocalDateTime completedDate;
    private TransactionStatus status;

    public Transaction(String id, String ticketId, double amount) {
        this.id = id;
        this.ticketId = ticketId;
        this.amount = amount;
        this.status = TransactionStatus.INITIATED;
        this.initiatedDate = LocalDateTime.now();
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public void setInitiatedDate(LocalDateTime initiatedDate) {
        this.initiatedDate = initiatedDate;
    }

    public void setCompletedDate(LocalDateTime completedDate) {
        this.completedDate = completedDate;
    }
}
