package controller;

import database.DataBase;
import geolocation.controller.GoogleMapsAPIImpl;
import gmailApi.SendMailSSL;
import model.Request;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;

public class CourierController implements ICourierController {

    private static final int SPEED = 60;
    private DataBase dataBase;
    private static final String PASSWORD = "courierPass";

    private boolean inSystem = false;

    public CourierController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean isInSystem() {
        return inSystem;
    }

    public List<Integer> getIdRequests() {
        List<Integer> result = new ArrayList<>();

        dataBase.getRequests().forEach(r -> result.add(r.getId()));
        return result;
    }

    @Override
    public void deliver(int id) {

        Request removed = dataBase.removeRequest(id);
        dataBase.deliver(removed);

        SendMailSSL.sendLetter(removed.getEmail(), "Delivery service", "product:\n" +
                removed.getProduct().getName() + "\ndelivered by address:\n" + removed.getTo().getFormattedAddress());


    }

    @Override
    public void checkIn(String password) {
        if (password != null && password.equals(PASSWORD)) {
            inSystem = true;
        }
    }

    @Override
    public void checkOut() {
        inSystem = false;
    }
}
