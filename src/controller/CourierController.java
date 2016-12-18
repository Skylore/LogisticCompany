package controller;

import database.DataBase;
import gmailApi.SendMailSSL;
import model.Request;
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

    public List<String> getIdRequests() {
        List<String> result = new ArrayList<>();

        dataBase.getRequests().forEach(r -> result.add(r.getProduct().getName()));
        return result;
    }

    @Override
    public void deliver(String name) {

        Request removed = dataBase.removeRequest(name);
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
