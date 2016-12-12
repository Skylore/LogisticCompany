package tests;

import controller.CourierConroller;
import database.DataBase;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import model.Product;
import model.Request;

public class TestDelivery {

    public static void main(String[] args) {

        testDelivery();
    }

    private static void testDelivery() {
        GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
        DataBase dataBase = new DataBase();
        Location location = googleMapsAPI.findLocation("Україна", "Київ", "Старокиївська", "10");
        Location location1 = googleMapsAPI.findLocation("Україна", "Київ", "Павлівська", "29");

        System.out.println(googleMapsAPI.getDistance(location, location1));

        dataBase.addRequest(new Request(0, "iturchin98@gmail.com",
                new Product("Ipad", 1, 4), 1000, location, location1));

        new CourierConroller(dataBase).deliver();
    }
}
