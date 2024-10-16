package io.praveen10.applications.parkinglot.account.model;

import io.praveen10.applications.parkinglot.common.model.Address;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Account {
    private String id;
    private String email;
    private String username;
    private String password;
    private LocalDateTime lastAccessed;
    private Address contact;
    private PersonalInfo personalInfo;

    public Account(String email, String username, String password) {
        id = UUID.randomUUID().toString();
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public abstract boolean isAdmin();
}
