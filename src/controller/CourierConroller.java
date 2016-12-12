package controller;

import database.DataBase;
import geolocation.controller.GoogleMapsAPIImpl;
import gmailApi.SendMailSSL;
import model.Request;

public class CourierConroller {

    DataBase dataBase = new DataBase();

   /* public void deliver() {

        Request last = dataBase.getRequests().poll();

        long timeForDeliver = ((long) (new GoogleMapsAPIImpl().getDistance(last.getFrom(),
                last.getTo()) / 100));

        new Thread(() -> {
            try {
                Thread.sleep(timeForDeliver * 1000);
                delivery(last);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }*/

    private void delivery(Request request) {

        if (request.getFrom() == request.getTo()) {
            SendMailSSL.sendLetter(request.getEmail(),
                    "Delivery center", "product\n" + request.getProduct().toString() +
                            "\nhas been delivered");
            System.out.println("\nproduct has been delivered");
            return;
        }

        // TODO write here delivery logic
    }
}
