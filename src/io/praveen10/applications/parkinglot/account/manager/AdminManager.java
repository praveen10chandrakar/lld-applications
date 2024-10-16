package io.praveen10.applications.parkinglot.account.manager;

import io.praveen10.applications.parkinglot.common.exceptions.AccountException;
import io.praveen10.applications.parkinglot.account.model.Admin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AdminManager {
    private Map<String, Admin> admins;

    public AdminManager() {
        admins = new ConcurrentHashMap<>();
    }

    public void addAdmin(Admin admin) throws AccountException{
        if(admins.containsKey(admin.getId())) {
            throw new AccountException("Admin already present.");
        }
        admins.put(admin.getId(), admin);
    }

}
