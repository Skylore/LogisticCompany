package tests;

import controller.ClientController;
import controller.CourierController;
import database.DataBase;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import model.Product;

public class TestDelivery {

    public static void main(String[] args) {

        testDelivery();
    }

    private static void testDelivery() {
        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
        DataBase dataBase = new DataBase();
        ClientController clientController = new ClientController(dataBase);
        Location location = googleMapsAPI.findLocation("Україна", "Київ", "Ревуцького", "7");
        Location location1 = googleMapsAPI.findLocation("Україна", "Київ", "Тампере", "9");

        System.out.println(googleMapsAPI.getDistance(location, location1));

        clientController.sendProductRequest(new Product("Ipad", 2, 3),
                "iturchin98@gmail.com", location, location1);

        System.out.println(clientController.whereIsMyProduct(0));
        new CourierController(dataBase).deliver(0);
        System.out.println(clientController.whereIsMyProduct(0));

        new Thread(() -> {
            try {
                Thread.sleep(85000);
                System.out.println(clientController.whereIsMyProduct(0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
