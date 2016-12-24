package controller;

import database.DataBase;
import gmailApi.SendMailSSL;
import model.Request;

import java.util.ArrayList;
import java.util.List;

public class CourierController implements ICourierController {

    private DataBase dataBase;
    private static final String PASSWORD = "courierPass";

    public CourierController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public List<String> getRequestsName() {
        List<String> result = new ArrayList<>();

        dataBase.getRequests().values().forEach(r -> result.add(r.getProduct().getName()));
        return result;
    }

    @Override
    public void deliver(String name) {

        Request removed = dataBase.requestToDelivered(name);

        SendMailSSL.sendLetter(removed.getEmail(), "Delivery service", "product:\n" +
                removed.getProduct().getName() + "\ndelivered by address:\n" + removed.getTo().getFormattedAddress());


    }

    @Override
    public void checkIn(String password) throws IllegalAccessException {
        if (!password.equals(PASSWORD)) {
            throw new IllegalAccessException("wrong exception");
        }
    }
}
