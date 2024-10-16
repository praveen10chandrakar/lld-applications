package io.praveen10.applications.parkinglot.account.model;

public class Admin extends Account {

    public Admin(String email, String username, String password) {
        super(email, username, password);
    }

    public boolean isAdmin() {
        return true;
    }
}
