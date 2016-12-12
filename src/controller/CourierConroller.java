package controller;

import database.DataBase;
import geolocation.controller.GoogleMapsAPIImpl;
import gmailApi.SendMailSSL;
import model.Request;

public class CourierConroller {

    private static final int SPEED = 60;
    private DataBase dataBase;

    public CourierConroller(DataBase dataBase) {
        this.dataBase = dataBase;
    }

   public void deliver() {

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
}
