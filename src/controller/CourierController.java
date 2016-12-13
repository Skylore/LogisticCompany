package controller;

import database.DataBase;
import geolocation.controller.GoogleMapsAPIImpl;
import gmailApi.SendMailSSL;
import model.Request;

import java.security.AccessControlException;

public class CourierController implements ICourierController{

    private static final int SPEED = 60;
    private DataBase dataBase;
    private static final String PASSWORD = "courierPass";

    private boolean inSystem = false;

    public CourierController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

   public void deliver() {

       if (!inSystem) {
           throw new AccessControlException("incorrect password");
       }

        Request last = dataBase.removeRequest();

        long timeForDeliver = ((long) (new GoogleMapsAPIImpl().getDistance(last.getFrom(),
                last.getTo()) / SPEED));

       new Thread(() -> {
            System.err.println("startOfDelivery");
            try {
                Thread.sleep(timeForDeliver * 1000);
                dataBase.deliver(last);
                System.err.println("delivered");

                SendMailSSL.sendLetter(last.getEmail(), "Delivery service", "product:\n" +
                        last.getProduct().getName() + "\ndelivered by address:\n" + last.getTo().getFormattedAddress());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
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
